package com.playground.user.payload.support;

import org.springframework.core.convert.converter.Converter;

public class CustomDateStringConverter implements Converter<String, String> {

    @Override
    public String convert(String source) {
        return source.replaceAll("-", "");
    }
}
