package com.example.project1currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@org.springframework.stereotype.Component
public class Component {

    @Autowired
    CurrencyPostgresRepository currencyPostgresRepository;

    @Autowired
    CurrencyService currencyService;

    @Scheduled(fixedDelay = 2700000)
    public void getCurrencyScheduled(){
        currencyPostgresRepository.createCurrency(currencyService.getCurrency());
    }

}
