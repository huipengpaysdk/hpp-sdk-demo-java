package com.huipengpay.demo.web;

import com.huipengpay.demo.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 订单列表
 */
@WebServlet(value = "/orders")
public class OrdersListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("data", Repository.findAll());
        req.getRequestDispatcher("/order-list.jsp").forward(req, resp);
    }

}
