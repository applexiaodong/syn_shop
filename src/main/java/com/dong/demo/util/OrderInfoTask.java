package com.dong.demo.util;

import com.dong.demo.domain.*;
import com.dong.demo.service.EcGoodsService;
import com.dong.demo.service.EcOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * 定时获取订单信息 任务
 * @description
 * @author: wangxd
 * @create: 2019-07-02
 **/
//@Component
public class OrderInfoTask {
    //日志
    private static Logger logger = LoggerFactory.getLogger(OrderInfoTask.class);

    @Value("${address.base_url}")
    private String baseUrl;

    //订单
    @Autowired
    private EcOrderService ecOrderService;

    //商品
    @Autowired
    private EcGoodsService ecGoodsService;

    //token信息
//    public static String accessToken = AccessTokenTask.accessToken;

    //每隔5分钟，启动后立即执行
//    @Scheduled(fixedRate = 300000)
    //定义任务每23小时执行一次
//    @Scheduled(cron = "0 0 0-23 * * ? ")
    //每隔十分钟，启动后不执行
//    @Scheduled(cron = "0 0/1 * * * ? ")
    public void getAccessToken(){
        //1、获取消息队列中信息
        Map<String,Object> orderParam = new Hashtable<>();
        String accessToken = AccessTokenTask.accessToken;
        orderParam.put("token",accessToken);
        orderParam.put("method","getOrderPushMsg");
        orderParam.put("type",1);//订单类
        orderParam.put("is_del",0);
        ReturnData orderNoData = HttpClientUtil.postMethod(baseUrl,orderParam);

        //token 过期
        if (orderNoData != null){
            if (!orderNoData.isSuccess()){
                logger.info("错误信息"+orderNoData.getResultMessage());
            }
            //返回信息成功
            if (orderNoData.isSuccess()){
                if (orderNoData.getResult()!= null){
                    List<Map> orderNoList = (List)orderNoData.getResult();
                    if (orderNoList != null && orderNoList.size()>0){
                        Map<String,Object> orderNoParam;
                        //遍历订单消息
                        for (Map orderNoMap : orderNoList) {
                            //订单信息
                            Map msgInfo = (Map) orderNoMap.get("msgInfo");
                            //type 0：新增；1：更新 2：取消
                            if (orderNoMap.get("type")!= null && orderNoMap.get("orderNo")!=null){
                                orderNoParam = new HashMap<>();
                                //获取订单编号
                                String orderNo = (String)orderNoMap.get("orderNo");
                                orderNoParam.put("orderNo",orderNo);
                                orderNoParam.put("method","queryOrderInfo");
                                orderNoParam.put("token",accessToken);
                                ReturnData returnData = HttpClientUtil.postMethod(baseUrl,orderNoParam);
                                //保存到电商本地
                                Map<String,Object> orderInfo = (Map<String, Object>) returnData.getResult();
                                if (orderInfo!=null){
                                    //新增
                                    if ((Integer)orderNoMap.get("type")==0){
                                        //保存订单消息
                                        EcOrder ecOrder = new EcOrder();

                                        //保存订单发货信息
                                        EcOrderInfo ecOrderInfo = new EcOrderInfo();
                                        //生成订单id和orderNo
                                        Map<String,Object> orderKeys = ecOrderService.generateOrderKey("EC_ORDER");
                                        if (orderKeys != null){
                                            ecOrder.setOrderId((BigDecimal) orderKeys.get("orderId"));
                                            ecOrder.setOrderNo((String) orderKeys.get("orderNo"));
                                            ecOrderInfo.setOrderId((BigDecimal) orderKeys.get("orderId"));
                                        }
                                        //订单状态
                                        if(orderInfo.get("orderState")!=null){
                                            Integer orderState = (Integer)orderInfo.get("orderState");
                                           if(orderState==0){//取消
                                               ecOrder.setOrderStatus(-10L);
                                           }else if(orderState==1){
                                               ecOrder.setOrderStatus(20L);
                                           }
                                        }
                                        //支付状态
                                        ecOrder.setPayId(new BigDecimal(0L));
                                        ecOrder.setPayStatus((short)2);//已支付
                                        //配送方式（0、包邮 1、快递 2、物流 3、EMS 4、自提 5、到付）

                                        //支付方式（1、支付宝-手机 2、支付宝 3、微信支付）

                                        ecOrder.setOrderTime((Date) orderInfo.get("createTime"));
                                        //价格相关
                                        ecOrder.setPaidAmount(new BigDecimal((Double) orderInfo.get("orderPrice")));//已付金额
                                        ecOrder.setGoodsPromoAmount(new BigDecimal((Double) orderInfo.get("orderPrice")));//商品金额(促销金额)
                                        ecOrder.setShopCashCouponDiffAmount(new BigDecimal(0L));//店铺代金券抵扣金额
                                        ecOrder.setTotalAmount(new BigDecimal((Double) orderInfo.get("orderPrice")));//卖家应收总金额
                                        ecOrder.setpPointCount(0L);//平台积分使用数量
                                        ecOrder.setpPointDiffAmount(new BigDecimal(0L));//平台积分抵扣金额
                                        ecOrder.setpDiffAmount(new BigDecimal(0L));//平台抵扣金额
                                        ecOrder.setPayableAmount(new BigDecimal((Double) orderInfo.get("orderPrice")));//应支付金额
                                        //发票信息
                                        ecOrder.setIsInvoiceRequired((short)1);
                                        //免运费

                                        ecOrderService.saveOrder(ecOrder);

                                        //发货信息
                                        ecOrderInfo.setAddrProvinceId(transferProvinceAddress((String) orderInfo.get("province")));
                                        ecOrderInfo.setAddrCityId(transferProvinceAddress((String) orderInfo.get("city")));
                                        ecOrderInfo.setAddrDistrictId(transferProvinceAddress((String) orderInfo.get("county")));
                                        ecOrderInfo.setAddrAddress((String)orderInfo.get("address"));
                                        ecOrderInfo.setContactName((String) orderInfo.get("name"));
                                        ecOrderInfo.setContactTel((String) orderInfo.get("mobile"));
                                        ecOrderInfo.setAddrPostcode((String) orderInfo.get("zip"));
                                        ecOrderInfo.setMbrRemark((String) orderInfo.get("remark"));
                                        if ((Integer)orderInfo.get("selectedInvoiceTitle")==2){//发票抬头
                                            ecOrderInfo.setInvoiceTitle((String) orderInfo.get("companyName"));
                                        }
                                        ecOrderService.saveOrderInfo(ecOrderInfo);

                                        //商品sku信息
                                        List<Map> orderDetails = (List<Map>) orderInfo.get("orderDetails");
                                        if (orderDetails != null && orderDetails.size() >0){
                                            EcOrderSku orderSku;
                                            Map<String,Object> orderSkuKeys;
                                            //遍历保存
                                            for (Map orderDetail : orderDetails) {
                                                orderSku = new EcOrderSku();
                                                //生成订单id和orderNo
                                                orderSkuKeys = ecOrderService.generateOrderKey("EC_ORDER_SKU");
                                                if (orderSkuKeys != null){
                                                    orderSku.setOrderSkuId((BigDecimal) orderKeys.get("orderId"));
                                                }
                                                orderSku.setOrderId(ecOrder.getOrderId());
                                                orderSku.setSkuId(new BigDecimal((String) orderDetail.get("sku")));//sku_id
                                                orderSku.setBuyQty((Long)orderDetail.get("num"));//数量
                                                orderSku.setBuyPrice(new BigDecimal((Double) orderDetail.get("price")));//单价
                                                orderSku.setBuyPrice(new BigDecimal((Double) orderDetail.get("price")));//单价
                                                //根据sku_id查询ec_goods_sku信息
                                                EcGoodsSku goodsSku = ecGoodsService.selectGoodsSkuById(new BigDecimal((String) orderDetail.get("sku")));
                                                if (goodsSku != null) {
                                                    orderSku.setGoodsId(goodsSku.getGoodsId());
                                                    orderSku.setSkuName(goodsSku.getSkuName());
                                                    orderSku.setSkuPrice(goodsSku.getSkuPrice());
                                                    orderSku.setSkuPropTextList(goodsSku.getSkuPropTextList());
                                                }
                                                orderSku.setPromoId(new BigDecimal(0L));
                                                orderSku.setPromoLauncher(0L);
                                                ecOrderService.saveOrderSku(orderSku);
                                            }
                                        }

                                    }

                                }
                            }

                        }
                    }else{
                        logger.info("暂无订单信息");
                    }
                }
            }
        }
    }

    //收货地址转换-省
    public BigDecimal transferProvinceAddress(String addressCode){
        BigDecimal provinceId = new BigDecimal(0);
        Map<String,Object> provinceMap = new HashMap<>();
        provinceMap.put("token",AccessTokenTask.accessToken);
        provinceMap.put("method","allProvincesAddress");
        ReturnData returnData = HttpClientUtil.postMethod(baseUrl,provinceMap);
        if (returnData.isSuccess()){
            List<Map> provinceList = (List<Map>) returnData.getResult();
            String provinceName = "";
            for (Map province:provinceList){
                if (province.get("id").equals(addressCode)){
                    provinceName = (String) province.get("name");
                }
            }
            if (!provinceName.equals("")){
                //根据省份名称查询省份id
                provinceId = ecOrderService.getAreaId(provinceName);
            }
        }
        return provinceId;
    }

    //收货地址转换-市
    public BigDecimal transferCityAddress(String provinceCode,String cityCode){
        BigDecimal provinceId = new BigDecimal(0);
        Map<String,Object> cityMap = new HashMap<>();
        cityMap.put("token",AccessTokenTask.accessToken);
        cityMap.put("method","citysByProvinceId");
        cityMap.put("id",provinceCode);

        ReturnData returnData = HttpClientUtil.postMethod(baseUrl,cityMap);
        if (returnData.isSuccess()){
            List<Map> cityList = (List<Map>) returnData.getResult();
            String cityName = "";
            for (Map city :cityList){
                if (city.get("id").equals(cityCode)){
                    cityName = (String) city.get("name");
                }
            }
            if (!cityName.equals("")){
                //根据省份名称查询省份id
                provinceId = ecOrderService.getAreaId(cityName);
            }
        }
        return provinceId;
    }
}
