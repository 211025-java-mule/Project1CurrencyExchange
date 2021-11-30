package com.example.project1currency;


import java.util.List;

public interface CurrencyRepository {

    void createCurrency(Currency currencyOutput);
    List<Currency> createJsonCurrency(Currency currencyOutput);
}
