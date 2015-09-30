package com.huipengpay.demo.web;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.huipengpay.demo.Order;
import com.huipengpay.demo.Repository;
import com.huipengpay.sdk.core.AES;
import com.huipengpay.sdk.core.HppConfig;
import com.huipengpay.sdk.protocol.TradeSuccessEvent;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 入账通知
 */
public class CallbackNotifyServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final InputStreamReader reader = new InputStreamReader(req.getInputStream(), Charsets.UTF_8);

        String notifyData = AES.decrypt(CharStreams.toString(reader), HppConfig.PASSPORT_KEY);
        TradeSuccessEvent event = JSON.parseObject(notifyData, TradeSuccessEvent.class);

        LOGGER.warn("收到[{}][{}]入账响应", event.getPayInterface(), event.getOrderNumber());

        if (StringUtils.equals(event.getStatus(), "'NOTIFY_CONFIRM'")) {
            LOGGER.warn("订单[%s]已支付成功", event.getOrderNumber());
            Order _order = Repository.find(event.getOrderNumber());
            _order.setStatus(event.getStatus());
            Repository.save(_order);

            LOGGER.warn("[{}][{}]跳转响应完成,开始发货...", event.getPayInterface(), event.getOrderNumber());
        }
    }
}
