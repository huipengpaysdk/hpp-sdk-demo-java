package com.huipengpay.sdk.protocol;

import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 支付成功后由hpp webhook系统推送的事件
 */
public final class TradeSuccessEvent extends BaseHppResponse {

    private BigDecimal amount; //交易金额(单位为分)
    private String appId; //应用id
    private Date payAt; //支付时间
    private String orderNumber; //订单号
    private String tradeSn;  //汇鹏流水号
    private String settleDate; //出账日期
    private String status; //交易状态

    private Map<String, Object> extra = Maps.newHashMap();//支付渠道的额外数据

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
