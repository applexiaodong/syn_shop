package com.dong.demo.service;

import com.dong.demo.domain.EcGoods;
import com.dong.demo.domain.EcOrder;
import com.dong.demo.domain.EcOrderInfo;
import com.dong.demo.domain.EcOrderSku;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EcOrderService {

    //保存订单信息
    int saveOrder(EcOrder order);

    //保存订单发货信息
    int saveOrderInfo(EcOrderInfo orderInfo);

    //保存订单商品sku信息
    int saveOrderSku(EcOrderSku orderSku);

    //调用存储过程生成订单id和编号
    Map generateOrderKey(String tableName);

    //根据地址名称查询地址id
    BigDecimal getAreaId(String areaName);
}
