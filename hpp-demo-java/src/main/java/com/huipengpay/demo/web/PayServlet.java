package com.huipengpay.demo.web;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.huipengpay.demo.Order;
import com.huipengpay.demo.Repository;
import com.huipengpay.sdk.core.NetworkUtils;
import com.huipengpay.sdk.protocol.PayResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 支付请求
 */
@WebServlet(value = "/pay")
public class PayServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNumber = UUID.randomUUID().toString();

        Order order = new Order();
        order.setAppId("CA_APP-ID-0001");
        order.setPayInterface(req.getParameter("pay_interface"));
        order.setOrderNumber(orderNumber);
        order.setOrderSubject("java-demo-1分钱支付体验");
        order.setOrderDescribe(req.getParameter("order_describe"));
        order.setAmount(new BigDecimal(req.getParameter("order_amount")));
        order.setCustomerIp(NetworkUtils.getRequestRealIp(req));
        order.setReturnUrl("http://127.0.0.1:8080/callback/return/" + orderNumber);

        LOGGER.warn("商户订单[{}]签名完成,开始使用proxy进行请求输出", orderNumber);
        PayResponse payResponse = getPayService().startPay(order.createPayRequest());
        order.setResponseData(payResponse);
        Repository.save(order);

        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(JSON.toJSONString(ImmutableMap.of("orderNumber", orderNumber)));
    }
}
