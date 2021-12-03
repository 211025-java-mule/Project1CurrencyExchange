package com.example.project1currency.Statistic;

import java.util.HashMap;
import java.util.Map;

public class StatisticResult {

    private Map<String, Double> averageResult = new HashMap<>();
    private Map<String, Double> standardDeviationResult = new HashMap<>();

    public Map<String, Double> getAverageResult() {
        return averageResult;
    }

    public Map<String, Double> getStandardDeviationResult() {
        return standardDeviationResult;
    }

}
