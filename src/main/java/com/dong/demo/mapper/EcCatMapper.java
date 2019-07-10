package com.dong.demo.mapper;

import com.dong.demo.domain.EcCat;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;

public interface EcCatMapper {
    int deleteByPrimaryKey(BigDecimal catId);

    int insert(EcCat record);

    int insertSelective(EcCat record);

    EcCat selectByPrimaryKey(BigDecimal catId);

    int updateByPrimaryKeySelective(EcCat record);

    int updateByPrimaryKey(EcCat record);

    Page<EcCat> findAllCat();
}