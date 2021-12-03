package com.example.project1currency.Statistic;

public enum StatisticEnum {

    AVERAGE("getAverageCalculation"),
    STANDARD_DEVIATION("getStandardDeviationCalculation");

    private String displayName;

    StatisticEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
