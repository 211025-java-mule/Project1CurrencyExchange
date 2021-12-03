package com.example.project1currency.SQL;

public class SQLStatement {
    public final static String SQL= "select * from currency join rate on currency.id=rate.currency;";
    public final static String SQL_ONE_CURRENCY= "select * from currency join rate on currency.id=rate.currency where rate.name='%s';";

}
