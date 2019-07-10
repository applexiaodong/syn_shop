package com.dong.demo.test;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.json.JSONTokener;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description
 * @author: wangxd
 * @create: 2019-06-14
 **/
public class DemoTest {

    private final static Logger logger = LoggerFactory.getLogger(DemoTest.class);
//    @Test
//    public void printLog(){
//        //由低到高   trace<debug<info<warn<error
//        logger.trace("这是trace日志...跟踪");
//        logger.debug("这是debug日志...调试");
//        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
//        logger.info("这是info日志...信息");
//        logger.warn("这是warn日志...警告");
//        logger.error("这是error日志...错误");
//
//    }

    @Test
    public void testJosnData(){
//        String str = "[{\"name\":\"name1\"},{\"name2\":\"name2\"}]";
//        String str = "{\"name\":\"name1\"}";
        String str = "1234";
            Object typeObject = null;
            try {
                typeObject = new JSONTokener(str).nextValue();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (typeObject instanceof org.json.JSONArray) {
                System.out.print("JSONArray");
            } else if (typeObject instanceof org.json.JSONObject) {
                System.out.print("JSONObject");
            }else{
                System.out.println("Common String");
            }
    }
}
