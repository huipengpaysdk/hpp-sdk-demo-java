package com.huipengpay.sdk.core;

import java.nio.charset.Charset;

/**
 * rest api地址等常规配置
 * //TODO:更换成自己的配置
 */
public final class HppConfig {
    public static final String KEY_HEADER_MT_SNO = "x-mt-sno";
    //默认的编码格式
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    //默认的Post请求格式
    public static final String REQUEST_CONTENT_TYPE = "text/plain";

    //PC网关支付路径
    //订单查询路径
    public static final String URL_PC_GATEWAY_PAY = "https://cashier.huipengpay.com/api/v1/pay";
    public static final String URL_ORDER_QUERY = "https://cashier.huipengpay.com/api/v1/order-query";



    public static String PASSPORT_KEY = "Hl0kp77WbCF8m0dF+IzOsQ==";//加密私钥
    public static String PASSPORT_NUMBER = "HPPPN20157083500001";//商户号
}
