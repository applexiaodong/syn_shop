package com.dong.demo.controller;

import com.alibaba.fastjson.JSON;
import com.dong.demo.domain.EcCat;
import com.dong.demo.domain.Girl;
import com.dong.demo.domain.ResultMessage;
import com.dong.demo.domain.ReturnData;
import com.dong.demo.service.EcCatService;
import com.dong.demo.util.HttpClientUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cat")
public class EcCatController {

    @Autowired
    private EcCatService ecCatService;

    private final static Logger logger = LoggerFactory.getLogger(EcCatController.class);

    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public ResultMessage findAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        ResultMessage result = new ResultMessage();
        PageHelper.startPage(page,limit);

        PageInfo<EcCat> pageInfo = new PageInfo<>(ecCatService.findAll());
        result.setCode("0");
        result.setMsg("OK");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    @RequestMapping(value = "/list2",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public ResultMessage findGirl(@RequestBody Girl girl){
        ResultMessage result = new ResultMessage();
        result.setCode("0");
        result.setMsg("OK");
        List<Girl> girls = new ArrayList<>();
        girls.add(girl);
        result.setData(girls);
        return result;
    }

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Nice to meet you";
    }

    @RequestMapping(value = "/clientGetOne")
    public ResultMessage httpClientGetMethodOne(){

        String info = null;
        ResultMessage result = new ResultMessage();
        // 1、获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //配置参数
        StringBuffer params = new StringBuffer();
        try {
            params.append("page="+ URLEncoder.encode("2","utf-8"));
            params.append("&");
            params.append("limit=10");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 2、创建Get请求http://localhost:8088/cat/list1
        HttpGet httpGet = new HttpGet("http://localhost:8088/cat/list?"+params);
        //设置头信息
        httpGet.setHeader("Accept-Charset","utf-8");
        //设置连接超时时间、获取数据超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(6000)
                .setConnectionRequestTimeout(6000)
                .setSocketTimeout(12000).build();
        httpGet.setConfig(requestConfig);

        // 3、响应模型
        CloseableHttpResponse response = null;
        try {
            // 4、由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 5、从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            System.out.println("响应状态码为:" + response.getStatusLine().getStatusCode());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            result.setMsg("测试连接OK");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            info="连接接口超时,请稍后重试!";
            result.setMsg(info);

            logger.info("接口超时:---------------------"+info);
            logger.error("连接接口超时", e);
            return result;
//
        }catch (SocketTimeoutException e) {
            e.printStackTrace();
            info="调用接口超时,请稍后重试!";
            result.setMsg(info);

            logger.info("接口超时:---------------------"+info);
            logger.error("调用接口超时", e);
            return result;

        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result.setCode("0");
        return result;
    }

    /** 
    * @Description: post请求
    * @Param:  
    * @return:  
    * @Author: wangxd 
    * @Date: 2019/6/14 
    */ 
    @RequestMapping(value = "/clientPostOne")
    public ResultMessage httpClientPostMethodOne(){

        String info = null;
        ResultMessage result = new ResultMessage();
        // 1、获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、创建Post请求http://localhost:8088/cat/list1
        HttpPost httpPost = new HttpPost("http://localhost:8088/cat/list2");

        //参数
        Girl girl = new Girl();
        girl.setId(5);
        girl.setSex("女");
        girl.setAge(25);
        girl.setName("Marry");
        //将object类转化为json字符串
        String jsonString = JSON.toJSONString(girl);
        //创建请求实体，post请求将参数放在请求体里传送
        StringEntity entity = new StringEntity(jsonString,"UTF-8");
        httpPost.setEntity(entity);

        //设置头信息
        httpPost.setHeader("Content-Type","application/json;charset=utf8");
        //设置连接超时时间、获取数据超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(6000)
                .setConnectionRequestTimeout(6000)
                .setSocketTimeout(12000).build();
        httpPost.setConfig(requestConfig);

        // 3、响应模型
        CloseableHttpResponse response = null;
        try {
            // 4、由客户端执行(发送)Get请求
            response = httpClient.execute(httpPost);
            // 5、从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            System.out.println("响应状态码为:" + response.getStatusLine().getStatusCode());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            result.setMsg("测试连接OK");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            info="连接接口超时,请稍后重试!";
            result.setMsg(info);

            logger.info("接口超时:---------------------"+info);
            logger.error("连接接口超时", e);
            return result;
//
        }catch (SocketTimeoutException e) {
            e.printStackTrace();
            info="调用接口超时,请稍后重试!";
            result.setMsg(info);

            logger.info("接口超时:---------------------"+info);
            logger.error("调用接口超时", e);
            return result;

        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        result.setCode("0");
        return result;
    }

    /**
     * @Description: post请求获取授权
     * @Param:
     * @return:
     * @Author: wangxd
     * @Date: 2019/6/14
     */
    @RequestMapping(value = "/getAuthority")
    public ReturnData getClientAuthority() {

       String url = "http://localhost:8088/cat/list2";
       Map<String,Object> map = new HashMap<>();
       map.put("name","赵虎");
       map.put("age",23);
       map.put("sex","男");
//        Girl girl = new Girl();
//        girl.setId(5);
//        girl.setSex("女");
//        girl.setAge(25);
//        girl.setName("Marry");

       return HttpClientUtil.postMethod(url,map);
    }

}
