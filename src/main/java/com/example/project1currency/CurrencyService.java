package com.example.project1currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class CurrencyService {
    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());
    private ObjectMapper mapper;
    @Value("${url}")
    private String urlStringFromProperties;

    public CurrencyService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    private static HttpURLConnection getHttpURLConnection(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            log.error("400 Error connecting");
        }
        return connection;
    }
    private static InputStream getInputStream(HttpURLConnection connection) {
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            log.error("Could not open response");
        }
        return response;
    }
    private static String getBody(InputStream response) {
        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            log.error("Could not read response");
        }
        return body;
    }
    private static Currency getCurrencyOutput(ObjectMapper mapper, String body) {
        Currency output = new Currency();
        try {
            output = mapper.readValue(body, Currency.class);
        } catch (JsonProcessingException e) {
            log.error("Could not read response");
        }
        return output;
    }
    public Currency getCurrency() {

        HttpURLConnection httpURLConnection = null;
        log.info("tring to get data from url {}", urlStringFromProperties);
        try {
            httpURLConnection = getHttpURLConnection(new URL(urlStringFromProperties));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream response = getInputStream(httpURLConnection);
        String body = getBody(response);
        return getCurrencyOutput(mapper, body);
    }
}
