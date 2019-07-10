package com.dong.demo.service;

import com.dong.demo.domain.EcCat;
import com.dong.demo.domain.Girl;
import com.github.pagehelper.Page;

public interface EcCatService {

    Page<EcCat> findAll();
}
