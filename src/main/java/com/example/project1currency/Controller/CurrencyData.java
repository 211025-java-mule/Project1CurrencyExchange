package com.example.project1currency.Controller;

import com.example.project1currency.Currency;

import java.util.List;

public class CurrencyData {
    List<Currency> currencyList;

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
