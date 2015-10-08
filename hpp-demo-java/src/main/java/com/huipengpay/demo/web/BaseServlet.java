package com.huipengpay.demo.web;

import com.huipengpay.sdk.core.HppPayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class BaseServlet extends HttpServlet {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseServlet.class);
    private static HppPayService payService;

    protected HppPayService getPayService() {

        if (payService == null) {
            payService = new HppPayService();
        }

        return payService;
    }

    /**
     * 获取/url/:id中的id
     */
    protected String getLastParamsInPath(HttpServletRequest req) {
        String uri = req.getRequestURI();

        return StringUtils.substringAfterLast(uri, "/");
    }

}
