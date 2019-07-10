package com.dong.demo.service;

import com.dong.demo.domain.Girl;
import com.github.pagehelper.Page;

import java.util.List;

public interface GirlService {

    int addGirl(Girl girl);

    Page<Girl> findAll();
}
