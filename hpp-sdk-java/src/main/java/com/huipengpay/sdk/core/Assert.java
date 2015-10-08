package com.huipengpay.sdk.core;

import org.apache.commons.lang3.StringUtils;

public class Assert {

    public static void isNotBlank(String target) {

        if (StringUtils.isBlank(target)) {
            throw new IllegalArgumentException("Target is blank");
        }

    }

}
