package com.example.project1currency;

import java.sql.Timestamp;
import java.util.Map;


public class Currency {
    public boolean success;
    public Timestamp timestamp;
    public String base;
    public String date;
    public Map<String, Double> rates;

    public void setSuccess(boolean success) { this.success = success;}

    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

    public void setBase(String base) { this.base = base; }

    public void setDate(String date) { this.date = date; }

    public void setRates(Map<String, Double> rates) { this.rates = rates;}

    public boolean isSuccess() {
        return success;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
