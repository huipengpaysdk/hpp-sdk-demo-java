package com.huipengpay.demo.web;

import com.huipengpay.demo.Order;
import com.huipengpay.demo.Repository;
import com.huipengpay.sdk.protocol.OrderQueryRequest;
import com.huipengpay.sdk.protocol.OrderQueryResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 订单查询请求
 */
@WebServlet(urlPatterns = "/order-query/*")
public class OrderQueryServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderNumber = getLastParamsInPath(req);
        checkNotNull(orderNumber);

        Order order = Repository.find(orderNumber);

        if (order.isSuccess()) {
            LOGGER.warn("订单[{}]已完成,无需重新处理...", orderNumber);
            resp.sendRedirect("/orders");
            return;
        }

        OrderQueryRequest request = new OrderQueryRequest(order.getResponseData().getTradeSn());
        OrderQueryResponse response = getPayService().startOrderQuery(request);
        order.setStatus(response.getStatus());
        Repository.save(order);

        resp.sendRedirect("/orders");
    }
}
