package com.dong.demo.service.impl;

import com.dong.demo.domain.EcCat;
import com.dong.demo.domain.Girl;
import com.dong.demo.mapper.EcCatMapper;
import com.dong.demo.mapper.GirlMapper;
import com.dong.demo.service.EcCatService;
import com.dong.demo.service.GirlService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ecCatService")
public class EcCatServiceImpl implements EcCatService {

    @Autowired
    private EcCatMapper ecCatMapper;

    @Override
    public Page<EcCat> findAll() {
        return ecCatMapper.findAllCat();
    }
}
