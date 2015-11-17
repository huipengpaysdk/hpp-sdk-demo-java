package com.huipengpay.demo.web;

import com.huipengpay.demo.Order;
import com.huipengpay.demo.Repository;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 跳转到支付渠道
 */
@WebServlet(urlPatterns = "/jump/*")
public class JumpToServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderNumber = getLastParamsInPath(req);
        checkNotNull(orderNumber);

        Order order = Repository.find(orderNumber);

        RequestDispatcher dispatcher;
        if (StringUtils.equals(order.getPayInterface(), "WEIXIN_NATIVE")) {
            req.setAttribute("code_url", order.getResponseData().getWXQRPayUrl());
            dispatcher = req.getRequestDispatcher("/barcode-proxy.jsp");
        } else {
            req.setAttribute("rawHtml", order.getResponseData().getRawHtml());
            dispatcher = req.getRequestDispatcher("/form-proxy.jsp");
        }

        dispatcher.forward(req, resp);
    }
}
