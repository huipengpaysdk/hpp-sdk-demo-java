package com.huipengpay.sdk.protocol;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 发起支付时的响应
 */
public final class PayResponse extends BaseHppResponse {

    public PayResponse() {
    }

    private String rawHtml;//带输出的pc跳转网关
    private String preChargeTicket;//预支付凭证

    private String orderNumber;//商户订单号
    private String tradeSn;//hpp流水号

    private Map<String, Object> extra = Maps.newHashMap();//单独支付渠道的数据

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPreChargeTicket() {
        return preChargeTicket;
    }

    public void setPreChargeTicket(String preChargeTicket) {
        this.preChargeTicket = preChargeTicket;
    }

    public String getRawHtml() {
        return rawHtml;
    }

    public void setRawHtml(String rawHtml) {
        this.rawHtml = rawHtml;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }

    /**
     * 获取微信支付二维码地址的快捷方法
     */
    public String getWXQRPayUrl() {
        return MapUtils.getString(extra, "code_url");
    }
}
