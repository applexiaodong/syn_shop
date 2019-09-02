package com.dong.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dong.demo.domain.EcGoods;
import com.dong.demo.domain.ResultMessage;
import com.dong.demo.domain.ReturnData;
import com.dong.demo.domain.vo.EcGoodsSkuVo;
import com.dong.demo.service.EcGoodsService;
import com.dong.demo.util.AccessTokenTask;
import com.dong.demo.util.HttpClientUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class EcGoodsController {

    @Autowired
    private EcGoodsService ecGoodsService;

    private final static Logger logger = LoggerFactory.getLogger(EcGoodsController.class);

    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public ResultMessage findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int limit){
        ResultMessage result = new ResultMessage();
        PageHelper.startPage(page,limit);

        PageInfo<Map> pageInfo = new PageInfo<>(ecGoodsService.findAll());
        result.setCode("0");
        result.setMsg("OK");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        logger.info("查询出的数量为-->"+ pageInfo.getTotal());
        return result;
    }

    @RequestMapping(value = "/getGoods",produces = "application/json;charset=UTF-8")
    public ResultMessage postMethodTest(@RequestBody Map param){
        ResultMessage result = new ResultMessage();

        result.setCode("0");
        result.setMsg("OK");
        List<Map> data = new ArrayList<>();
        data.add(param);
        result.setCount(Long.parseLong(String.valueOf(data.size())));
        result.setData(data);
        return result;
    }

    /** 
    * @Desc: post请求获取授权
    * @Author: wangxd 
    * @Date: 2019/7/13 
    */ 
    @RequestMapping(value = "/getSingleGoods")
    public ReturnData getClientAuthority(@RequestBody EcGoods ecGoods) {

       String url = "http://localhost:8088/goods/getGoods";
       logger.info(ecGoods.getGoodsId().toString());
       logger.info(ecGoods.getGoodsName());
       Map<String,Object> map = new HashMap<>();
//               ecGoodsService.selectSingleGoodsByCondition(ecGoods);
       List<Map> propMap = ecGoodsService.selectSingleGoodsProp(ecGoods);
       map.put("attr",propMap);

       return HttpClientUtil.postMethod(url,map);
    }

    @Value("${address.token_url}")
    private String tokenUrl;

    @Value("${address.base_url}")
    private String baseUrl;

    //token授权
//    public static String token = AccessTokenTask.accessToken;

    /** 
    * @Desc: 同步保存商品信息
    * @Param:  
    * @return:  
    * @Author: wangxd 
    * @Date: 2019/7/2 
    */ 
    @RequestMapping(value = "/synchroSaveGoods")
    public ReturnData saveEcGoods(@RequestParam(value = "list",required = true) List<BigDecimal> goodsIdList){

        ReturnData data = new ReturnData();
        if (goodsIdList!=null && goodsIdList.size()>0){
            //遍历商品id，循环同步
            for (BigDecimal goodsId : goodsIdList) {
                //1、获取token授权
                //2、获取本地商品信息
                //根据商品id查询商品SKU信息
                List<EcGoodsSkuVo> goodsList = ecGoodsService.selectGoodsSkuByGoodsId(goodsId);

                //构建参数
                EcGoods ecGoods = new EcGoods();
                ecGoods.setGoodsId(goodsId);
                Map goods = ecGoodsService.selectSingleGoodsByCondition(ecGoods);
                BigDecimal catId = null;
                if (goods != null) {
                    catId = (BigDecimal) goods.get("CAT_ID");
                }
                //获取商品目录信息
                /**
                 *  a、根据商品目录查询一级目录
                 *  b、获取中电建平台的所有一级目录
                 *  c、匹配一级目录
                 *  d、根据一级目录查询所有二级、三级目录
                 *  e、匹配录 查询最终的categoryCode
                 * */
                String token = AccessTokenTask.accessToken;
                String categoryCode = getCategoryCode(catId,token);

                //商品属性
                List<Map> propMap = ecGoodsService.selectSingleGoodsProp(ecGoods);

                //商品图片信息
                List<Map> imageInfo = ecGoodsService.selectGoodsImageInfo(goodsId);

                //3、构造接口参数
                Map<String,Object> postParam = new HashMap<>();
                postParam.put("method","importGoodsInfo");
                postParam.put("token",token);

                //4、循环调用接口
                for (EcGoodsSkuVo goodsMap : goodsList) {
                    goodsMap.setImg(imageInfo);//图片
                    goodsMap.setAttrs(propMap);//商品属性
                    if (categoryCode != null) {
                        goodsMap.setCategoryCode(categoryCode);
                    }
                    postParam.put("sku",goodsMap.getSku());
                    postParam.put("goodsInfo", JSON.toJSONString(goodsMap));
                    ReturnData returnData = HttpClientUtil.postMethod(baseUrl,postParam);
                    if (!returnData.isSuccess()){
                        data.setSuccess(false);
                        data.setResultMessage(returnData.getResultMessage());
                        data.setResultCode(returnData.getResultCode());
                        data.setResult("同步失败!");
                        return data;
                    }
                }
                data.setSuccess(true);
                data.setResultMessage("ok");
                data.setResultCode("0");
                data.setResult("同步成功！");
            }

        }else{
            data.setSuccess(false);
            data.setResultMessage("同步商品不能为空");
        }

        return data;
    }

    /**
     * @Desc: 同步更新商品信息
     * @Param:
     * @return:
     * @Author: wangxd
     * @Date: 2019/7/2
     */
    @RequestMapping(value = "/synchroUpdateGoods")
    public ReturnData updateEcGoods(@RequestParam(value = "list",required = true) List<BigDecimal> goodsIdList){
        ReturnData data = new ReturnData();
        if (goodsIdList!=null && goodsIdList.size()>0){
            //1、获取token授权
            String token = AccessTokenTask.accessToken;

            for (BigDecimal goodsId : goodsIdList){
                //2、获取本地商品信息
                //根据商品id查询商品SKU信息
                List<EcGoodsSkuVo> goodsList = ecGoodsService.selectGoodsSkuByGoodsId(goodsId);

                //构建参数
                EcGoods ecGoods = new EcGoods();
                ecGoods.setGoodsId(goodsId);
                //商品属性
                List<Map> propMap = ecGoodsService.selectSingleGoodsProp(ecGoods);

                //根据商品id查询商品图片
                List<Map> imageInfo = ecGoodsService.selectGoodsImageInfo(goodsId);

                //3、构造更新商品基本信息接口参数
                Map<String,Object> postParam = new HashMap<>();
                postParam.put("method","updateGoodsInfo");
                postParam.put("token",token);

                //4、构造更新商品图片信息参数
                Map<String,Object> imageParam = new HashMap<>();
                imageParam.put("method","updateSkuImgInfo");
                imageParam.put("token",token);
                List<String> skuList = goodsList.stream().map(EcGoodsSkuVo::getSku).distinct().collect(Collectors.toList());
                String sku = String.join(",",skuList);
                imageParam.put("sku",sku);
                //构造图片数据
                List<Map> skuImgInfo = new ArrayList<>();

                //5、循环调用接口
                for (EcGoodsSkuVo goodsMap : goodsList) {
                    goodsMap.setAttrs(propMap);
                    postParam.put("sku",goodsMap.getSku());
                    postParam.put("goodsInfo", JSON.toJSONString(goodsMap));
                    //更新商品信息
                    ReturnData returnData = HttpClientUtil.postMethod(baseUrl,postParam);
                    if (!returnData.isSuccess()){
                        data.setSuccess(false);
                        data.setResultMessage(returnData.getResultMessage());
                        data.setResultCode(returnData.getResultCode());
                        data.setResult("同步更新商品失败!");
                        return data;
                    }

                    //图片数据
                    Map<String,Object> imageMap = new HashMap<>();
                    imageMap.put("sku",goodsMap.getSku());
                    imageMap.put("imgInfo",imageInfo);
                    skuImgInfo.add(imageMap);
                }
                if (skuImgInfo.size()>0){
                    imageParam.put("skuImgInfo",JSON.toJSONString(skuImgInfo, SerializerFeature.DisableCircularReferenceDetect));//解决fastjson重复引用
                }

                //更新图片信息
                ReturnData dataBack = HttpClientUtil.postMethod(baseUrl,imageParam);
                if (!dataBack.isSuccess()){
                    data.setSuccess(false);
                    data.setResultMessage(dataBack.getResultMessage());
                    data.setResultCode(dataBack.getResultCode());
                    data.setResult("同步更新图片失败!");
                    return data;
                }
                data.setSuccess(true);
                data.setResultMessage("ok");
                data.setResultCode("0");
                data.setResult("同步更新商品成功");
            }

        } else{
            data.setSuccess(false);
            data.setResultMessage("同步商品不能为空");
        }

        return data;
    }

    /** 
    * @Desc: 获取中电建平台上categoryCode
    * @Param:  
    * @return:  
    * @Author: wangxd 
    * @Date: 2019/7/2 
    */ 
    public String getCategoryCode(BigDecimal catId,String token){
        String categoryCode = "";
        //根据catId查询商品目录路径
        String catNamePath = ecGoodsService.selectCatPathById(catId);
        String parentCat = "";
        String childCat = "";
        if (catNamePath != null) {
            String[] catArray = catNamePath.split("/");
            if (catArray != null) {
                if (catArray.length==1){//顶级目录
                    parentCat = catArray[0];
                    childCat = catArray[0];
                }else if (catArray.length>1){//子目录
                    parentCat = catArray[0];
                    childCat = catArray[catArray.length-1];
                }
            }
        }
        //接口查询所有一级目录
        Map<String,Object> parentMap = new HashMap<>();
        parentMap.put("token",token);
        parentMap.put("method","allFirstCategory");
        ReturnData firstCat = HttpClientUtil.postMethod(baseUrl,parentMap);
        List<Map<String,Object>> firstCatMap = (List<Map<String, Object>>) firstCat.getResult();

        String parentCatCopy = "";
        String childCatCopy = "";
        for (Map<String, Object> catMap : firstCatMap) {
            if (catMap.get("name").equals(parentCat)){
                parentCatCopy = String.valueOf(catMap.get("code"));
                break;
            }
        }

        if (!parentCat.isEmpty() && !childCat.isEmpty()){
            if (parentCat.equals(childCat)){//顶级目录
                categoryCode = parentCatCopy;
            }else{//子目录
                //接口查询2、3级目录
                Map<String,Object> childMap = new HashMap<>();
                childMap.put("token",token);
                childMap.put("method","queryCategoryInfoByPcode");
                childMap.put("pcode",parentCatCopy);
                ReturnData secondCat = HttpClientUtil.postMethod(baseUrl,childMap);
                List<Map<String,Object>> secondCatMap = (List<Map<String, Object>>) secondCat.getResult();
                for (Map<String, Object> cate : secondCatMap) {
                    if (cate.get("name").equals(childCat)){
                        childCatCopy = String.valueOf(cate.get("code"));
                        categoryCode = childCatCopy;
                        break;
                    }
                }
            }
        }

        return categoryCode;
    }

}
