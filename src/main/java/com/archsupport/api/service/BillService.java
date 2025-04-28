package com.archsupport.api.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.archsupport.api.api.OpenStatesApiClient;

@Service
public class BillService {

    @Autowired
    private OpenStatesApiClient openStatesApiClient;

    @Cacheable(value = "billsCache", key = "#queryParams.toString()")
    public String getBills(Map<String, String> queryParams) {
        return openStatesApiClient.fetchBills(queryParams);
    }

    @Cacheable(value = "billDetailsCache", key = "#billId")
    public String getBillDetails(String billId, Map<String, String> queryParams) {
        return openStatesApiClient.fetchBillDetails(billId, queryParams);
    }
}
