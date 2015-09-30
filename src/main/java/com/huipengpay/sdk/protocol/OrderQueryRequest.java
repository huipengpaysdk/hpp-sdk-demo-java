package com.huipengpay.sdk.protocol;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

/**
 * 客户端使用sdk发起订单状态查询时的参数载体
 */
public final class OrderQueryRequest extends BaseApiRequest {

    @NotBlank
    @SafeHtml
    private String tradeSn;

    public OrderQueryRequest(String tradeSn) {
        this.tradeSn = tradeSn;
    }

    public String getTradeSn() {
        return tradeSn;
    }

    public void setTradeSn(String tradeSn) {
        this.tradeSn = tradeSn;
    }
}