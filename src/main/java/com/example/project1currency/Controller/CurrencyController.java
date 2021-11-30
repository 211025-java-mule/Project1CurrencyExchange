package com.example.project1currency.Controller;

import com.example.project1currency.Currency;
import com.example.project1currency.CurrencyPostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyPostgresRepository currencyPostgresRepository;

    @Autowired
    Currency currencyOutput;


    @RequestMapping("/getPageData")
    public List<Currency> getPageData(){
        return currencyPostgresRepository.createJsonCurrency(currencyOutput);
    }

    @RequestMapping("/getCalculation")
    public String getCalculation(){
        ///localhost:8081/currency/calculate

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8081/currency/calculate";
        CurrencyData currencyData = new CurrencyData();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        currencyData.setCurrencyList(currencyPostgresRepository.createJsonCurrency(currencyOutput));
        final HttpEntity<CurrencyData> entity = new HttpEntity<>(currencyData,headers);
        ResponseEntity<String> response
                = restTemplate.postForEntity(resourceUrl, entity, String.class);

        return response.getBody();
    }

}
