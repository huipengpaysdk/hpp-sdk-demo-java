package com.huipengpay.sdk.core;

public class Non2xxResponseException extends RuntimeException {
    private String responseBody;

    public Non2xxResponseException(String message, String responseBody) {
        super(message);
        this.responseBody = responseBody;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
