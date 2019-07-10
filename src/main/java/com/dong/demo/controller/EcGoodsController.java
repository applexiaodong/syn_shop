package com.dong.demo.controller;

import com.dong.demo.domain.*;
import com.dong.demo.service.EcGoodsService;
import com.dong.demo.util.AccessTokenTask;
import com.dong.demo.util.AccessTokenThread;
import com.dong.demo.util.HttpClientUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.*;

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

//    @RequestMapping(value = "/list2",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
//    public ResultMessage findGirl(@RequestBody Girl girl){
//        ResultMessage result = new ResultMessage();
//        result.setCode("0");
//        result.setMsg("OK");
//        List<Girl> girls = new ArrayList<>();
//        girls.add(girl);
//        result.setData(girls);
//        return result;
//    }
//
//    @RequestMapping(value = "/hello")
//    public String hello(){
//        return "Nice to meet you";
//    }
//
//    @RequestMapping(value = "/clientGetOne")
//    public ResultMessage httpClientGetMethodOne(){
//
//        String info = null;
//        ResultMessage result = new ResultMessage();
//        // 1、获得Http客户端
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        //配置参数
//        StringBuffer params = new StringBuffer();
//        try {
//            params.append("page="+ URLEncoder.encode("2","utf-8"));
//            params.append("&");
//            params.append("limit=10");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        // 2、创建Get请求http://localhost:8088/cat/list1
//        HttpGet httpGet = new HttpGet("http://localhost:8088/cat/list?"+params);
//        //设置头信息
//        httpGet.setHeader("Accept-Charset","utf-8");
//        //设置连接超时时间、获取数据超时时间
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(6000)
//                .setConnectionRequestTimeout(6000)
//                .setSocketTimeout(12000).build();
//        httpGet.setConfig(requestConfig);
//
//        // 3、响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 4、由客户端执行(发送)Get请求
//            response = httpClient.execute(httpGet);
//            // 5、从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            System.out.println("响应状态码为:" + response.getStatusLine().getStatusCode());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//            result.setMsg("测试连接OK");
//        } catch (ConnectTimeoutException e) {
//            e.printStackTrace();
//            info="连接接口超时,请稍后重试!";
//            result.setMsg(info);
//
//            logger.info("接口超时:---------------------"+info);
//            logger.error("连接接口超时", e);
//            return result;
////
//        }catch (SocketTimeoutException e) {
//            e.printStackTrace();
//            info="调用接口超时,请稍后重试!";
//            result.setMsg(info);
//
//            logger.info("接口超时:---------------------"+info);
//            logger.error("调用接口超时", e);
//            return result;
//
//        }catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        result.setCode("0");
//        return result;
//    }
//
//    /**
//    * @Description: post请求
//    * @Param:
//    * @return:
//    * @Author: wangxd
//    * @Date: 2019/6/14
//    */
//    @RequestMapping(value = "/clientPostOne")
//    public ResultMessage httpClientPostMethodOne(){
//
//        String info = null;
//        ResultMessage result = new ResultMessage();
//        // 1、获得Http客户端
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        // 2、创建Post请求http://localhost:8088/cat/list1
//        HttpPost httpPost = new HttpPost("http://localhost:8088/cat/list2");
//
//        //参数
//        Girl girl = new Girl();
//        girl.setId(5);
//        girl.setSex("女");
//        girl.setAge(25);
//        girl.setName("Marry");
//        //将object类转化为json字符串
//        String jsonString = JSON.toJSONString(girl);
//        //创建请求实体，post请求将参数放在请求体里传送
//        StringEntity entity = new StringEntity(jsonString,"UTF-8");
//        httpPost.setEntity(entity);
//
//        //设置头信息
//        httpPost.setHeader("Content-Type","application/json;charset=utf8");
//        //设置连接超时时间、获取数据超时时间
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(6000)
//                .setConnectionRequestTimeout(6000)
//                .setSocketTimeout(12000).build();
//        httpPost.setConfig(requestConfig);
//
//        // 3、响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 4、由客户端执行(发送)Get请求
//            response = httpClient.execute(httpPost);
//            // 5、从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            System.out.println("响应状态码为:" + response.getStatusLine().getStatusCode());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//            result.setMsg("测试连接OK");
//        } catch (ConnectTimeoutException e) {
//            e.printStackTrace();
//            info="连接接口超时,请稍后重试!";
//            result.setMsg(info);
//
//            logger.info("接口超时:---------------------"+info);
//            logger.error("连接接口超时", e);
//            return result;
////
//        }catch (SocketTimeoutException e) {
//            e.printStackTrace();
//            info="调用接口超时,请稍后重试!";
//            result.setMsg(info);
//
//            logger.info("接口超时:---------------------"+info);
//            logger.error("调用接口超时", e);
//            return result;
//
//        }catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        result.setCode("0");
//        return result;
//    }
//
    /**
     * @Description: post请求获取授权
     * @Param:
     * @return:
     * @Author: wangxd
     * @Date: 2019/6/14
     */
    @RequestMapping(value = "/getSingleGoods")
    public ReturnData getClientAuthority(@RequestBody EcGoods ecGoods) {

       String url = "http://localhost:8088/goods/getGoods";
       logger.info(ecGoods.getGoodsId().toString());
       logger.info(ecGoods.getGoodsName());
       Map<String,Object> map = ecGoodsService.selectSingleGoodsByCondition(ecGoods);
       List<Map> propMap = ecGoodsService.selectSingleGoodsProp(ecGoods);
       map.put("attr",propMap);

       return HttpClientUtil.postMethod(url,map);
    }

    @Value("${address.token_url}")
    private String tokenUrl;

    @Value("${address.base_url}")
    private String baseUrl;

    //token授权
    public static String token = AccessTokenTask.accessToken;

    /** 
    * @Desc: 同步保存商品信息
    * @Param:  
    * @return:  
    * @Author: wangxd 
    * @Date: 2019/7/2 
    */ 
    @RequestMapping(value = "/synchroSaveGoods")
    public ReturnData saveEcGoods(@RequestParam(defaultValue = "417") BigDecimal goodsId){

        ReturnData data = new ReturnData();
        //1、获取token授权

        //2、获取本地商品信息
        //根据商品id查询商品SKU信息
        List<Map> goodsList = ecGoodsService.selectGoodsSkuByGoodsId(goodsId);

        //构建参数
        EcGoods ecGoods = new EcGoods();
        ecGoods.setGoodsId(goodsId);
        Map goods = ecGoodsService.selectSingleGoodsByCondition(ecGoods);
        BigDecimal catId = null;
        if (goods != null) {
            catId = (BigDecimal) goods.get("catId");
        }
        //获取商品目录信息
        /**
         *  a、根据商品目录查询一级目录
         *  b、获取中电建平台的所有一级目录
         *  c、匹配一级目录
         *  d、根据一级目录查询所有二级、三级目录
         *  e、匹配录 查询最终的categoryCode
         * */
        String categoryCode = getCategoryCode(catId,token);

        //商品属性
        List<Map> propMap = ecGoodsService.selectSingleGoodsProp(ecGoods);

        //商品图片信息
        List<Map> imageInfo = ecGoodsService.selectGoodsImageInfo(goodsId);

        //3、构造接口参数
        Map<String,Object> postParam = new HashMap<>();
        postParam.put("Method","importGoodsInfo");
        postParam.put("Token",token);

        //4、循环调用接口
        for (Map goodsMap : goodsList) {
            goodsMap.put("img",imageInfo);
            if (categoryCode != null) {
                goodsMap.put("categoryCode",categoryCode);
            }
            goodsMap.put("attr",propMap);
            postParam.put("Sku",goodsMap.get("sku"));
            postParam.put("goodsInfo",goodsMap);
            ReturnData returnData = HttpClientUtil.postMethod(baseUrl,postParam);
            if (!returnData.isSuccess()){
                data.setSuccess(false);
                data.setResultMessage(returnData.getResultMessage());
                return data;
            }
        }
        data.setSuccess(true);
        data.setResultMessage("ok");
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
    public ReturnData updateEcGoods(@RequestParam(defaultValue = "417") BigDecimal goodsId){

        ReturnData data = new ReturnData();
        //1、获取token授权
        String token = AccessTokenTask.accessToken;

        //2、获取本地商品信息
        //根据商品id查询商品SKU信息
        List<Map> goodsList = ecGoodsService.selectGoodsSkuByGoodsId(goodsId);

        //构建参数
        EcGoods ecGoods = new EcGoods();
        ecGoods.setGoodsId(goodsId);
        Map goods = ecGoodsService.selectSingleGoodsByCondition(ecGoods);
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
        String sku = "";
        //构造图片数据
        List<Map> skuImgInfo = new ArrayList<>();

        //5、循环调用接口
        for (Map goodsMap : goodsList) {
            goodsMap.put("attr",propMap);
            postParam.put("sku",goodsMap.get("sku"));
            postParam.put("goodsInfo",goodsMap);
            //更新商品信息
            ReturnData returnData = HttpClientUtil.postMethod(baseUrl,postParam);
            if (!returnData.isSuccess()){
                data.setSuccess(false);
                data.setResultMessage(returnData.getResultMessage());
                return data;
            }

            //图片数据
            sku += goodsMap.get("sku");
            Map<String,Object> imageMap = null;
            for (Map image : imageInfo){
                imageMap = new HashMap<>();
                imageMap.put("sku",goodsMap.get("sku"));
                imageMap.put("imgInfo",image);
                skuImgInfo.add(imageMap);
            }
        }
        if (skuImgInfo.size()>0){
            imageParam.put("skuImgInfo",skuImgInfo);
        }
        if (!sku.equals("")){
            imageParam.put("sku",sku);
        }

        //更新图片信息
        ReturnData dataBack = HttpClientUtil.postMethod(baseUrl,imageParam);
        if (!dataBack.isSuccess()){
            data.setSuccess(false);
            data.setResultMessage(dataBack.getResultMessage());
            return data;
        }

        data.setSuccess(true);
        data.setResultMessage("ok");
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
                if (catArray.length==2){//顶级目录
                    parentCat = catArray[0];
                    childCat = catArray[0];
                }else if (catArray.length>2){//子目录
                    parentCat = catArray[0];
                    childCat = catArray[catArray.length-2];
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
                parentCatCopy = (String) catMap.get("code");
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
                        childCatCopy = (String) cate.get("code");
                        categoryCode = childCatCopy;
                        break;
                    }
                }
            }
        }

        return categoryCode;
    }

}
