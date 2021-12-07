package com.example.project1currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.Properties;

public class ApplicationContext {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());
    private Properties properties;

    private void argsParser(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-d":
                        properties.setProperty("saveToDB", "true");
                        break;
                    case "-t":
                        properties.setProperty("saveToTxt", "true");
                        break;
                    case "--url":
                        i++;
                        properties.setProperty("url", args[i]);
                        break;
                    default:
                        log.trace("Unknown argument");
                        break;
                }
            }
        } else {
            try {
                properties.load(Currency.class.getClassLoader().getResourceAsStream("application.properties"));
            } catch (IOException e) {
                System.err.println("Configuration file not found");
            } catch (NullPointerException e){
                log.error("File not found");
            }
        }
    }


//    public ApplicationContext(String[] args) {
//        this.properties = new Properties();
//        this.mapper = new ObjectMapper();
//        argsParser(args);
//        this.currencyService = new CurrencyService(mapper);
//        this.currencyFileRepository = new CurrencyFileRepository(mapper);
//    }

}
