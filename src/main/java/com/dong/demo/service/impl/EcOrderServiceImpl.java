package com.dong.demo.service.impl;

import com.dong.demo.domain.EcOrder;
import com.dong.demo.domain.EcOrderInfo;
import com.dong.demo.domain.EcOrderSku;
import com.dong.demo.mapper.EcOrderInfoMapper;
import com.dong.demo.mapper.EcOrderMapper;
import com.dong.demo.mapper.EcOrderSkuMapper;
import com.dong.demo.service.EcOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service(value = "ecOrderService")
public class EcOrderServiceImpl implements EcOrderService {

    @Autowired
    private EcOrderMapper ecOrderMapper;

    @Autowired
    private EcOrderInfoMapper ecOrderInfoMapper;

    @Autowired
    private EcOrderSkuMapper ecOrderSkuMapper;

    @Override
    public int saveOrder(EcOrder order) {
        return ecOrderMapper.insert(order);
    }

    @Override
    public int saveOrderInfo(EcOrderInfo orderInfo) {
        return ecOrderInfoMapper.insert(orderInfo);
    }

    @Override
    public int saveOrderSku(EcOrderSku orderSku) {
        return ecOrderSkuMapper.insert(orderSku);
    }

    @Override
    public Map generateOrderKey(String tableName) {
        return ecOrderMapper.generateOrderKey(tableName);
    }

    @Override
    public BigDecimal getAreaId(String areaName) {
        return ecOrderMapper.getAreaId(areaName);
    }
}
