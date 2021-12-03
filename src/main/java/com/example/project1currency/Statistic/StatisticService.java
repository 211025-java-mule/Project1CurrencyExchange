package com.example.project1currency.Statistic;

import com.example.project1currency.ApplicationContext;
import com.example.project1currency.Controller.CurrencyData;
import com.example.project1currency.CurrencyPostgresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.example.project1currency.SQL.SQLStatement.SQL;
import static com.example.project1currency.SQL.SQLStatement.SQL_ONE_CURRENCY;
import static com.example.project1currency.Statistic.StatisticEnum.AVERAGE;
import static com.example.project1currency.Statistic.StatisticEnum.STANDARD_DEVIATION;

@Service
public class StatisticService {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());

    @Autowired
    CurrencyPostgresRepository currencyPostgresRepository;

    @Value("${calculationServiceURL}")
    private String urlCalculationService;

    private ResponseEntity<StatisticResult> getRequestCalculation(String statisticName, String currencyName){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format(urlCalculationService, statisticName);
        log.info("resource url {}", resourceUrl);
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final HttpEntity<CurrencyData> entity = new HttpEntity<>(getCurrencyData(currencyName),headers);
        ResponseEntity<StatisticResult> response
                = restTemplate.postForEntity(resourceUrl, entity, StatisticResult.class);
        return response;
    }
    public Map<String, Double> getAvg(String currencyName) {
        return getRequestCalculation(AVERAGE.getDisplayName(),currencyName).getBody().getAverageResult();
    }


    public Map<String, Double> getDev(String currencyName) {
        return getRequestCalculation(STANDARD_DEVIATION.getDisplayName(),currencyName).getBody().getStandardDeviationResult();
    }


    private CurrencyData getCurrencyData(String currencyName) {
        CurrencyData currencyData = new CurrencyData();
        if(currencyName != null){
            String SQL = String.format(SQL_ONE_CURRENCY, currencyName);
             currencyData.setCurrencyList(currencyPostgresRepository.createJsonCurrency(SQL));
        }else{
            currencyData.setCurrencyList(currencyPostgresRepository.createJsonCurrency(SQL));
        }
        return currencyData;

    }
}
