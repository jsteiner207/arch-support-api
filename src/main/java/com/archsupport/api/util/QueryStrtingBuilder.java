package com.archsupport.api.util;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.util.UriUtils;

public class QueryStrtingBuilder {
    
        public static String buildQueryString(Map<String, String> queryParams) {
        return queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + UriUtils.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
