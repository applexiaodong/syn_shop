package com.dong.demo.service.impl;

import com.dong.demo.domain.EcGoods;
import com.dong.demo.domain.EcGoodsSku;
import com.dong.demo.mapper.EcGoodsMapper;
import com.dong.demo.mapper.EcGoodsSkuMapper;
import com.dong.demo.service.EcGoodsService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service(value = "ecGoodsService")
public class EcGoodsServiceImpl implements EcGoodsService {

    @Autowired
    private EcGoodsMapper ecGoodsMapper;

    @Autowired
    private EcGoodsSkuMapper ecGoodsSkuMapper;

    @Override
    public Page<Map> findAll() {
        return ecGoodsMapper.findAllGoods();
    }

    @Override
    public Map selectSingleGoodsByCondition(EcGoods goods) {
        return ecGoodsMapper.selectSingleGoodsByCondition(goods);
    }

    @Override
    public List<Map> selectSingleGoodsProp(EcGoods goods) {
        return ecGoodsMapper.selectGoodsProp(goods);
    }

    @Override
    public List<Map> selectGoodsSkuByGoodsId(BigDecimal goodsId) {
        return ecGoodsMapper.selectGoodsSkuByGoodsId(goodsId);
    }

    @Override
    public List<Map> selectGoodsImageInfo(BigDecimal goodsId) {
        return ecGoodsMapper.selectGoodsImageInfo(goodsId);
    }

    @Override
    public String selectCatPathById(BigDecimal catId) {
        return ecGoodsMapper.selectCatPathById(catId);
    }

    @Override
    public EcGoodsSku selectGoodsSkuById(BigDecimal skuId) {
        return ecGoodsSkuMapper.selectByPrimaryKey(skuId);
    }


}
