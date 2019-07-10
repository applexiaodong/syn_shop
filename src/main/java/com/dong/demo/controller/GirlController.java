package com.dong.demo.controller;

import com.dong.demo.domain.Girl;
import com.dong.demo.service.GirlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/girl")
public class GirlController {

    @Autowired
    private GirlService girlService;

    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    public int addGirl(Girl girl){
        return girlService.addGirl(girl);
    }

    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public PageInfo<Girl> findAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<Girl> pageInfo = new PageInfo<>(girlService.findAll());
        return pageInfo;
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Nice to meet you";
    }
}
