package com.example.project1currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CurrencyFileRepository {
    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());
    private ObjectMapper objectMapper;
    @Value("${saveToTxt}")
    private Boolean aBoolean;

    public CurrencyFileRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static String getSerializedString(ObjectMapper mapper, Currency currencyOutput) {
        String serializedString= null;
        try {
            serializedString = mapper.writeValueAsString(currencyOutput);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize String");
        }
        return serializedString;
    }
    public void createOutputFile(Currency output) {
        File outputFile = new File("outputFileCurrency.txt");
        if(aBoolean.equals(true)) {
            try (FileWriter outpuFileWriter = new FileWriter(outputFile, true);) {
                outpuFileWriter.write(getSerializedString(objectMapper, output) + "\n");
            } catch (IOException e) {
                log.error("Output file error");
            }
        }
    }

}
