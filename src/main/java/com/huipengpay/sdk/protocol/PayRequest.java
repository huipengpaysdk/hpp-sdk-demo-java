package com.huipengpay.sdk.protocol;

import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 客户端使用sdk发起支付请求时的参数载体
 */
public final class PayRequest extends BaseApiRequest {

    @NotBlank
    @SafeHtml
    private String appId; //应用id

    @NotNull
    @SafeHtml
    private String payInterface;//支付渠道

    @NotBlank
    @SafeHtml
    private String orderNumber; //子订单号

    @NotBlank
    @SafeHtml
    private String orderSubject; //订单标题

    @SafeHtml
    private String orderDescribe; //订单内容

    @NotNull
    @DecimalMin("1")
    private BigDecimal amount; //金额

    @NotNull
    @SafeHtml
    private String customerIp; //持卡人ip

    @URL
    @SafeHtml
    private String returnUrl;//前台跳转地址

    private Map<String, Object> extra = Maps.newHashMap();//需带回的动态数据

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPayInterface() {
        return payInterface;
    }

    public void setPayInterface(String payInterface) {
        this.payInterface = payInterface;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
