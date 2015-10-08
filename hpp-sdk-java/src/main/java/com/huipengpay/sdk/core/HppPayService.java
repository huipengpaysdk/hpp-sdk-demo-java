package com.huipengpay.sdk.core;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.huipengpay.sdk.protocol.OrderQueryRequest;
import com.huipengpay.sdk.protocol.OrderQueryResponse;
import com.huipengpay.sdk.protocol.PayRequest;
import com.huipengpay.sdk.protocol.PayResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class HppPayService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HppPayService.class);
    private static HppHttpClient httpClient;
    private Validator validator;

    public HppPayService() {

        checkNotNull(HppConfig.PASSPORT_NUMBER);
        checkNotNull(HppConfig.PASSPORT_KEY);

        validator = Validation.buildDefaultValidatorFactory().getValidator();

        httpClient = HppHttpClient.getInstance();
    }

    public PayResponse startPay(PayRequest request) {

        validateRequestObj(request);

        LOGGER.debug("开始支付请求,订单号[{}]目标渠道[{}]", request.getOrderNumber(), request.getPayInterface());

        String payload = AES.encrypt(HppConfig.PASSPORT_KEY, JSON.toJSONString(request));

        String aesRaw = httpClient.sendHttpRequest(
                HppConfig.URL_PC_GATEWAY_PAY,
                payload,
                ImmutableMap.of(HppConfig.KEY_HEADER_MT_SNO, HppConfig.PASSPORT_NUMBER),
                HppConfig.DEFAULT_CHARSET, HppConfig.REQUEST_CONTENT_TYPE
        );

        PayResponse response = JSON.parseObject(AES.decrypt(HppConfig.PASSPORT_KEY, aesRaw), PayResponse.class);

        LOGGER.debug("请求结束,响应结果[{}][{}]", response.getResultCode(), response.getResultMsg());

        return response;
    }

    public OrderQueryResponse startOrderQuery(OrderQueryRequest request) {
        validateRequestObj(request);

        LOGGER.debug("开始查询订单[{}]的状态", request.getTradeSn());

        String payload = AES.encrypt(HppConfig.PASSPORT_KEY, JSON.toJSONString(request));

        String aesRaw = httpClient.sendHttpRequest(
                HppConfig.URL_ORDER_QUERY,
                payload,
                ImmutableMap.of(HppConfig.KEY_HEADER_MT_SNO, HppConfig.PASSPORT_NUMBER),
                HppConfig.DEFAULT_CHARSET, HppConfig.REQUEST_CONTENT_TYPE
        );
        OrderQueryResponse response = JSON.parseObject(AES.decrypt(HppConfig.PASSPORT_KEY, aesRaw), OrderQueryResponse.class);

        LOGGER.debug("请求结束,响应结果[{}][{}]", response.getResultCode(), response.getResultMsg());

        return response;
    }

    /**
     * 使用303对对象进行校验
     */
    private void validateRequestObj(Object target, Class... groups) {
        Set result = validator.validate(target, groups);

        if (CollectionUtils.isNotEmpty(result)) {
            StringBuilder builder = new StringBuilder();

            for (Object violation : result) {
                ConstraintViolation cv = (ConstraintViolation) violation;

                builder.append(" [").append(cv.getPropertyPath()).append(cv.getMessage()).append("] ");
            }
            String msg = builder.toString();
            LOGGER.error("请求参数不正确:[{}]", msg);

            throw new ServiceException(msg);
        }
    }

}
