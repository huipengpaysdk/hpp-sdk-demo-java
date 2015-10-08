package com.huipengpay.sdk.protocol;

/**
 * 汇鹏对外响应的统一封装
 */
public abstract class BaseHppResponse {

    public static final String RESP_SUCCESS = "SUCCESS";
    public static final String RESP_ERROR = "ERROR";
    public static final String RESP_SUCCESS_MSG = "OK";

    protected String resultCode;//api响应代码
    protected String resultMsg;//api响应消息
    protected String payInterface;//渠道代号

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getPayInterface() {
        return payInterface;
    }

    public void setPayInterface(String payInterface) {
        this.payInterface = payInterface;
    }
}
