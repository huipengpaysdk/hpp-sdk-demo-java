package com.huipengpay.sdk.core;

/**
 * 支付渠道的枚举
 */
public enum PayInterface {
    UNIONPAY_WEB, //银联PC网关
    UNIONPAY_MOBILE,//银联移动

    WX_QRCODE,//微信扫码支付

    ALIPAY_WEB,//支付宝网关
}
