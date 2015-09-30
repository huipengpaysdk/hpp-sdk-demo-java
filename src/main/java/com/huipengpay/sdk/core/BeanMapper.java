package com.huipengpay.sdk.core;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class BeanMapper {
    private static Mapper mapper = new DozerBeanMapper();

    public static <T> T map(Object source, Class<T> clazz) {
        return mapper.map(source, clazz);
    }

    public static void copy(Object source, Object target) {
        mapper.map(source, target);
    }
}
