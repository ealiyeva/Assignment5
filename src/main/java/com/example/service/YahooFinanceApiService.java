package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface YahooFinanceApiService {

    Logger logger = LoggerFactory.getLogger(YahooFinanceApiService.class);

    Object getStockIndexOfSpecifiedCompany(String provider,String index);
    Object getStockIndexOfSpecifiedCompany(String provider, List<String> index);

    default String notFoundMessage(String provider){
        logger.error("Provider is not found");
       return String.format("Provider that you entered is  wrong {%s} ",provider);
    }
    static String errorMessage(){
        logger.error("Cannot get data from API");
        return "Problem while getting data from Yahoo API";
    }
}
