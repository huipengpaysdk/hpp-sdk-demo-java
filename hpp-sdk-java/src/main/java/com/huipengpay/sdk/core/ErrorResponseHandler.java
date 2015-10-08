package com.huipengpay.sdk.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 如果不是2系列的响应则抛出异常
 */
public class ErrorResponseHandler implements ResponseHandler<String> {
    @Override
    public String handleResponse(HttpResponse response) throws IOException {
        String statusCode = response.getStatusLine().getStatusCode() + "";

        String responseBody = EntityUtils.toString(response.getEntity());

        if (!StringUtils.startsWith(statusCode, "2")) {
            String msg = String.format("[%s]~[%s]", response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            throw new Non2xxResponseException(msg, responseBody);
        }

        return responseBody;
    }
}
