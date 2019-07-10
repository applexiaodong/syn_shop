package com.dong.demo.util;

import com.dong.demo.domain.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 定时获取access_token 任务
 * @description
 * @author: wangxd
 * @create: 2019-07-02
 **/
@Component
public class AccessTokenTask {
    //日志
    private static Logger logger = LoggerFactory.getLogger(AccessTokenTask.class);

    @Value("${address.token_url}")
    private String tokenUrl;

    @Value("${token.esponse_type}")
    private String esponseType;

    @Value("${token.client_id}")
    private String clientId;

    @Value("${token.client_secret}")
    private String clientSecret;

    @Value("${token.corp_id}")
    private String corpId;

    public static String accessToken = null;

    @Scheduled(fixedRate = 300000)
    //定义任务每23小时执行一次
//    @Scheduled(cron = "0 0 0-23 * * ? ")
    //每隔十分钟
//    @Scheduled(cron = "0 0/10 * * * ? ")
    public String getAccessToken(){
        //1、获取token授权
        Map<String,Object> tokenParam = new HashMap<>();
        tokenParam.put("esponse_type",esponseType);
        tokenParam.put("client_id",clientId);
        tokenParam.put("client_secret",clientSecret);
        tokenParam.put("corp_id",corpId);

        ReturnData tokenData = HttpClientUtil.postMethod(tokenUrl,tokenParam);
        //token 过期
        if (tokenData != null){
            if (!tokenData.isSuccess() && tokenData.getResultCode().equals("2002")){
                logger.info("token已过期,请重新刷新或获取");
            }
            if (tokenData.isSuccess()){
                Map tokenBack = (Map) tokenData.getResult();
//                Map tokenBack2 = (Map) tokenBack1.get("result");
                accessToken =  (String) tokenBack.get("access_token");
                //有效时长
                Integer expireIn = (Integer)tokenBack.get("expire_in");
                logger.info("获取access_token成功，有效时长{},token:{}",expireIn,accessToken);
            }else{
                //重新获取,60秒后重新获取
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.getAccessToken();
            }
        }
        return accessToken;
    }
}
