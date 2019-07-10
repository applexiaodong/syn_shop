package com.dong.demo.service.impl;

import com.dong.demo.domain.Girl;
import com.dong.demo.mapper.GirlMapper;
import com.dong.demo.service.GirlService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "girlService")
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlMapper girlMapper;

    @Override
    public int addGirl(Girl girl) {
        return girlMapper.insert(girl);
    }

    @Override
    public Page<Girl> findAll() {
        return girlMapper.findAllGirl();
    }
}
