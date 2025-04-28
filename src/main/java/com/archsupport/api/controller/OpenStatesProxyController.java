package com.archsupport.api.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.archsupport.api.service.BillService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class OpenStatesProxyController {

    @Autowired
    private BillService billService;

    @GetMapping("bills")
    public String getLegislators(@RequestParam Map<String, String> queryParams) {
        return billService.getBills(queryParams);
    }

    @GetMapping("bills/ocd-bill/{billId}")
    public String getBillDetails(@PathVariable String billId, @RequestParam Map<String, String> queryParams){
        return billService.getBillDetails(billId, queryParams);
    }
}
