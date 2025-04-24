package com.archsupport.api.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

@RestController
@RequestMapping("/api/openstates")
@CrossOrigin(origins = "*")
public class OpenStatesProxyController {

    private final RestTemplate restTemplate;
    
    @Value("${openstates.api.key}")
    private String apiKey;


    public OpenStatesProxyController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Cacheable(value = "billsCache", key = "#queryParams.toString()")
    @GetMapping("bills")
    public String getLegislators(@RequestParam Map<String, String> queryParams) {
        String url = "https://v3.openstates.org/bills?" + buildQueryString(queryParams);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }

        private String buildQueryString(Map<String, String> queryParams) {
        return queryParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + UriUtils.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
