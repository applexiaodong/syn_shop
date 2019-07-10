package com.dong.demo.mapper;

import com.dong.demo.domain.Girl;
import com.github.pagehelper.Page;

import java.util.List;

public interface GirlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Girl record);

    int insertSelective(Girl record);

    Girl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Girl record);

    int updateByPrimaryKey(Girl record);

    Page<Girl> findAllGirl();
}