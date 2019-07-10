package com.dong.demo.util;

import com.dong.demo.domain.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Hashtable;
import java.util.Map;

/**
 * 定时获取access_token线程
 * @description
 * @author: wangxd
 * @create: 2019-07-02
 **/
public class AccessTokenThread implements Runnable{

    //日志
    private static Logger log = LoggerFactory.getLogger(AccessTokenThread.class);

    @Value("${address.token_url}")
    private String tokenUrl;

    //token值
    private static String accessToken = null;
    @Override
    public void run() {
        String accessToken = null;
        //1、获取token授权
        Map<String,Object> tokenParam = new Hashtable<>();
        tokenParam.put("esponse_type","token");
        tokenParam.put("client_id","123");
        tokenParam.put("client_secret","124");
        tokenParam.put("corp_id","345");

        while (true) {
            try {
                ReturnData tokenData = HttpClientUtil.postMethod(tokenUrl,tokenParam);
                Map tokenBack = (Map) tokenData.getResult();
                if (null != accessToken) {
                    log.info("获取access_token成功，有效时长{}秒 token:{}", tokenBack.get("expire_in"), tokenBack.get("token"));
                    // 休眠7000秒
                    Thread.sleep(((Long)tokenBack.get("expire_in") - 200) * 1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }

}
