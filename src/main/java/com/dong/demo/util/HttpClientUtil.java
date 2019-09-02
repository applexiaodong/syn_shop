package com.dong.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dong.demo.domain.ReturnData;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description 接口工具类
 * @author: wangxd
 * @create: 2019-06-17
 **/
public class HttpClientUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static ReturnData postMethod(String url, Object object){
        String info = null;
        ReturnData result = new ReturnData();
        // 1、获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2、创建Post请求http://localhost:8088/cat/list1
        HttpPost httpPost = new HttpPost(url);

        //3、设置头信息
//        httpPost.setHeader("Content-Type","application/json;charset=UTF-8");
//        httpPost.setHeader("Accept","application/json");
        //设置连接超时时间、获取数据超时时间
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(60000)
//                .setConnectionRequestTimeout(60000)
//                .setSocketTimeout(12000).build();
//        httpPost.setConfig(requestConfig);

        // 4、创建参数队列（键值对列表）
        Map<String,Object> map = (Map<String, Object>) object;
        List<NameValuePair> paramPairs = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            Object val = map.get(key);
            if (null != val){
                paramPairs.add(new BasicNameValuePair(key, val.toString()));
            }
        }
        UrlEncodedFormEntity entity;

        //4、创建json格式
        try {
            entity = new UrlEncodedFormEntity(paramPairs,"UTF-8");
            httpPost.setEntity(entity);
            String requestString = JSON.toJSONString(object);
            logger.info("请求内容为:" + requestString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5、响应模型
        CloseableHttpResponse response = null;
        try {

            // 4、由客户端执行(发送)Get请求
            response = httpClient.execute(httpPost);
            // 5、从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            logger.info("响应状态为:" + response.getStatusLine());
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("响应状态码为:" + statusCode);
            if (statusCode == 500){
                result.setSuccess(false);
                result.setResultMessage("内部错误");
                logger.error("错误内容"+responseEntity);
                return result;
            }
            if (responseEntity != null) {
                logger.info("响应内容长度为:" + responseEntity.getContentLength());
                //响应内容String
                String entityString = EntityUtils.toString(responseEntity);
                logger.info("响应内容为:" + entityString);

                /**
                 *判断返回类型result为何种类型
                 * 1 json数组
                 * 2 json对象
                 * 3 普通字符串
                 * */
                //String转为JsonObject
                JSONObject jsonObject = JSON.parseObject(entityString);
                Map<String,Object> resultMap = jsonObject;
                if (null != resultMap.get("resultCode")&& !((String)resultMap.get("resultCode")).equals("0000")){
                    result.setSuccess(false);
                    result.setResultCode((String)resultMap.get("resultCode"));
                    result.setResultMessage((String) resultMap.get("resultMessage"));
                    return result;
                }

                result.setResult(resultMap.get("result"));

            }

            result.setResultMessage("测试连接OK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (ConnectTimeoutException e) {
            e.printStackTrace();
            info="连接接口超时,请稍后重试!";
            result.setResultMessage(info);

            logger.info("接口超时:---------------------"+info);
            logger.error("连接接口超时", e);
            return result;
//
        }catch (SocketTimeoutException e) {
            e.printStackTrace();
            info="调用接口超时,请稍后重试!";
            result.setResultMessage(info);

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
        result.setSuccess(true);
        result.setResultCode("0");
        return result;
    }
}
