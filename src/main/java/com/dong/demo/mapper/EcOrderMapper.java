package com.dong.demo.mapper;

import com.dong.demo.domain.EcOrder;
import java.math.BigDecimal;
import java.util.Map;

public interface EcOrderMapper {
    int deleteByPrimaryKey(BigDecimal orderId);

    int insert(EcOrder record);

    int insertSelective(EcOrder record);

    EcOrder selectByPrimaryKey(BigDecimal orderId);

    int updateByPrimaryKeySelective(EcOrder record);

    int updateByPrimaryKey(EcOrder record);

    //调用存储过程生成订单id和编号
    Map generateOrderKey(String tableName);

    BigDecimal getAreaId(String areaName);
}