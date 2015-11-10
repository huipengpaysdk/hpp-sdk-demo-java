package com.huipengpay.sdk.protocol;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户端使用sdk发起订单状态查询时的响应结果
 */
public final class OrderQueryResponse extends BaseHppResponse {

    public OrderQueryResponse() {
    }

    private String appId;
    private String orderNumber;//商户订单号
    private String tradeSn;//hpp流水号
    private BigDecimal amount;
    private String status; //交易状态
    private Date payAt; //支付时间

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }
}