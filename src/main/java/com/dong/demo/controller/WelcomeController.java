package com.dong.demo.controller;


import com.dong.demo.domain.Boy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    public String findGoods(){
        return "goodsList";
    }

}
