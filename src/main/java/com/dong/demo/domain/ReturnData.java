package com.dong.demo.domain;

import java.util.List;
import java.util.Map;

/**
 * 接口返回数据
 * */
public class ReturnData {

    //状态码
    private boolean success;

    //消息
    private String resultMessage;

    //错误码
    private String resultCode;

    //数据
    private Object result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }



}
