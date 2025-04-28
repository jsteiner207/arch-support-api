package com.archsupport.api.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.archsupport.api.util.QueryStrtingBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OpenStatesApiClient {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openstates.api.key}")
    private String apiKey;

    public String fetchBills(Map<String, String> queryParams){
        String url = "https://v3.openstates.org/bills?" + QueryStrtingBuilder.buildQueryString(queryParams);
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

    public String fetchBillDetails(String billId, Map<String, String> queryParams){
        String url = "https://v3.openstates.org/bills/ocd-bill/" + billId + "?include=actions&include=votes&include=documents&include=sponsorships";
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
}
            

