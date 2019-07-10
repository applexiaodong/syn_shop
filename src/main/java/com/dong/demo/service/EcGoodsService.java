package com.dong.demo.service;

import com.dong.demo.domain.EcGoods;
import com.dong.demo.domain.EcGoodsSku;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EcGoodsService {

    Page<Map> findAll();

    Map selectSingleGoodsByCondition(EcGoods goods);

    List<Map> selectSingleGoodsProp(EcGoods goods);

    //根据商品id查询商品sku信息
    List<Map> selectGoodsSkuByGoodsId(BigDecimal goodsId);

    //根据商品id查询商品图片信息
    List<Map> selectGoodsImageInfo(BigDecimal goodsId);

    //根据catId查询目录信息
    String selectCatPathById(BigDecimal catId);

    //根据sku_id查询商品sku信息
    EcGoodsSku selectGoodsSkuById(BigDecimal skuId);
}
