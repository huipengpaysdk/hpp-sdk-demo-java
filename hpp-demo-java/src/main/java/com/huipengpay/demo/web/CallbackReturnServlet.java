package com.huipengpay.demo.web;

import com.huipengpay.demo.Order;
import com.huipengpay.demo.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 跳转通知
 */
@WebServlet(urlPatterns = "/callback/return/*")
public class CallbackReturnServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderNumber = getLastParamsInPath(req);
        checkNotNull(orderNumber);
        LOGGER.warn("收到订单[{}]的跳转响应", orderNumber);

        Order order = Repository.find(orderNumber);
        if (order == null) {
            throw new ServletException(String.format("订单[%s]不存在", orderNumber));
        }

        if (!order.isSuccess()) {
            LOGGER.warn("订单[%s]尚未收到入账响应,进行主动查询", orderNumber);
            resp.sendRedirect("/order-query/" + orderNumber);
            return;
        }

        LOGGER.warn("[{}]跳转响应完成,开始输出付款完成界面", orderNumber);
        req.getRequestDispatcher("pay-result.html").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
