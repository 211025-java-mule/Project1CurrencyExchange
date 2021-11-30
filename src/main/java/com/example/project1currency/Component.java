package com.example.project1currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@org.springframework.stereotype.Component
public class Component {

    @Autowired
    CurrencyPostgresRepository currencyPostgresRepository;
    @Autowired
    Currency currencyOutput;
    @Autowired
    CurrencyService currencyService;



    @Scheduled(fixedDelay = 180000)
    public void siema(){
        currencyPostgresRepository.createCurrency(currencyService.getCurrency());
    }

}
