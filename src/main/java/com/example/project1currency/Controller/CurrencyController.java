package com.example.project1currency.Controller;

import com.example.project1currency.ApplicationContext;
import com.example.project1currency.Currency;
import com.example.project1currency.CurrencyPostgresRepository;
import com.example.project1currency.Statistic.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.project1currency.SQL.SQLStatement.SQL;
import static com.example.project1currency.SQL.SQLStatement.SQL_ONE_CURRENCY;
import static com.example.project1currency.Statistic.StatisticEnum.AVERAGE;
import static com.example.project1currency.Statistic.StatisticEnum.STANDARD_DEVIATION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());

    @Autowired
    CurrencyPostgresRepository currencyPostgresRepository;

    @Autowired
    StatisticService statisticService;


    @RequestMapping(method=GET, value={"/getPageData","/getPageData/{currencyName}"})
    public List<Currency> getPageData(@PathVariable(required = false) String currencyName){
        if(currencyName != null){
            String SQL = String.format(SQL_ONE_CURRENCY,currencyName);
            return currencyPostgresRepository.createJsonCurrency(SQL);
        }
        return currencyPostgresRepository.createJsonCurrency(SQL);

    }


    @RequestMapping(method=GET, value={"/{statisticName}","/{statisticName}/{currencyName}"})
    public Map<String, Double> getCalculation(@PathVariable String statisticName, @PathVariable(required = false) String currencyName) {
        if (statisticName.equals(AVERAGE.getDisplayName())) {
            return statisticService.getAvg(currencyName);
        }
        if (statisticName.equals(STANDARD_DEVIATION.getDisplayName())) {
            return statisticService.getDev(currencyName);
        }

        return new HashMap<>();


    }






}
