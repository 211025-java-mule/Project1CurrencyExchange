package com.example.project1currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;


@Component
public class CurrencyPostgresRepository implements CurrencyRepository{

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());
    @Autowired
    private Connection connection;
    @Value("${saveToDB}")
    private Boolean aBoolean;
    @Override
    public void createCurrency(Currency currencyOutput) {


        if(aBoolean.equals(true)) {
            try {
                log.info("creating prepare statement");
                PreparedStatement preparedStatementCurrency =
                        connection.prepareStatement("insert into currency(success, timestamp, base, date) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatementRate = connection.prepareStatement("insert into rate(name, value, currency) values (?, ?, ?);");
                preparedStatementCurrency.setBoolean(1, currencyOutput.isSuccess());
                preparedStatementCurrency.setTimestamp(2, currencyOutput.getTimestamp());
                preparedStatementCurrency.setString(3, currencyOutput.getBase());
                preparedStatementCurrency.setString(4, currencyOutput.getDate());
                preparedStatementCurrency.executeUpdate();
                ResultSet generatedKeys = preparedStatementCurrency.getGeneratedKeys();
                long i = generatedKeys.next() ? generatedKeys.getLong(1) : 0;
                for (Map.Entry<String, Double> entry : currencyOutput.getRates().entrySet()) {
                    preparedStatementRate.setString(1, entry.getKey());
                    preparedStatementRate.setDouble(2, entry.getValue());
                    preparedStatementRate.setLong(3, i);
                    preparedStatementRate.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Currency> createJsonCurrency(String SQL) {

        List<Currency> currencies = new ArrayList<>();


        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()) {
                Currency currency = new Currency();
                Map<String,Double> rates = new HashMap<>();
                currency.setSuccess(rs.getBoolean("success"));
                currency.setTimestamp(rs.getTimestamp("timestamp"));
                currency.setDate(rs.getString("date"));
                currency.setBase(rs.getString("base"));
                rates.put(rs.getString("name"), rs.getDouble("value"));
                currency.setRates(rates);

                currencies.add(currency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }
}
