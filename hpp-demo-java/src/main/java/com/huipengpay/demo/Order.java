package com.huipengpay.demo;

import com.huipengpay.sdk.core.BeanMapper;
import com.huipengpay.sdk.protocol.PayRequest;
import com.huipengpay.sdk.protocol.PayResponse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单
 */
public class Order {

    public static final String STATUS_SUCCESS = "NOTIFY_CONFIRM";

    private String appId;
    private String payInterface;
    private String orderNumber;
    private String orderSubject;
    private String orderDescribe; //订单内容
    private BigDecimal amount; //金额
    private String customerIp; //持卡人ip
    private String returnUrl;//前台跳转地址

    private Date createOn = new Date();
    private String status = "TRADE_CREATED"; //订单状态
    private PayResponse responseData; //响应数据

    public PayRequest createPayRequest() {
        return BeanMapper.map(this, PayRequest.class);
    }

    public boolean isSuccess() {
        return StringUtils.equals(status, "");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCreateOn() {
        return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(createOn);
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getOrderDescribe() {
        return orderDescribe;
    }

    public void setOrderDescribe(String orderDescribe) {
        this.orderDescribe = orderDescribe;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }

    public String getPayInterface() {
        return payInterface;
    }

    public void setPayInterface(String payInterface) {
        this.payInterface = payInterface;
    }

    public PayResponse getResponseData() {
        return responseData;
    }

    public void setResponseData(PayResponse responseData) {
        this.responseData = responseData;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
