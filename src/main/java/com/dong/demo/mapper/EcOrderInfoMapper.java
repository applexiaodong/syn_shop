package com.dong.demo.mapper;

import com.dong.demo.domain.EcOrderInfo;
import java.math.BigDecimal;

public interface EcOrderInfoMapper {
    int deleteByPrimaryKey(BigDecimal orderId);

    int insert(EcOrderInfo record);

    int insertSelective(EcOrderInfo record);

    EcOrderInfo selectByPrimaryKey(BigDecimal orderId);

    int updateByPrimaryKeySelective(EcOrderInfo record);

    int updateByPrimaryKey(EcOrderInfo record);
}