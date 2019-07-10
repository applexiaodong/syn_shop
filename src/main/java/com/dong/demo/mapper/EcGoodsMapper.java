package com.dong.demo.mapper;

import com.dong.demo.domain.EcCat;
import com.dong.demo.domain.EcGoods;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EcGoodsMapper {
    int deleteByPrimaryKey(BigDecimal goodsId);

    int insert(EcGoods record);

    int insertSelective(EcGoods record);

    EcGoods selectByPrimaryKey(BigDecimal goodsId);

    int updateByPrimaryKeySelective(EcGoods record);

    int updateByPrimaryKeyWithBLOBs(EcGoods record);

    int updateByPrimaryKey(EcGoods record);

    //分页查询商品
    Page<Map> findAllGoods();

    //根据条件查询单一商品
    Map selectSingleGoodsByCondition(EcGoods goods);

    //根据商品id查询商品属性
    List<Map> selectGoodsProp(EcGoods goods);

    //根据商品id查询商品sku信息集合
    List<Map> selectGoodsSkuByGoodsId(BigDecimal goodsId);

    //查询商品图片信息
    List<Map> selectGoodsImageInfo(BigDecimal goodsId);

    //根据catId查询目录信息
    String selectCatPathById(BigDecimal catId);



}