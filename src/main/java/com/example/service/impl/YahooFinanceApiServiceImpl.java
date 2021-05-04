package com.example.service.impl;

import com.example.service.YahooFinanceApiService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Singleton
public class YahooFinanceApiServiceImpl implements YahooFinanceApiService {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceApiServiceImpl.class);


    @Override
    public Object getStockIndexOfSpecifiedCompany(String provider, String index){
        if (!provider.equals("YAHOO")) {
            logger.warn("Provide the correct provider");
           return notFoundMessage(provider);
        }
        else{
            try {
                logger.info("Will call API og YAHOO");
                Stock stock = YahooFinance.get(index);
                BigDecimal price = stock.getQuote().getPrice();
                logger.info("Get the response from Yahoo API");
                JSONObject json = new JSONObject();
                json.put(index, price);
                logger.info("Put the price tho JSONObject");
                return json;
            }
            catch (IOException ex){
                logger.error(ex.getMessage());
                return YahooFinanceApiService.errorMessage();
            }
        }

    }

    @Override
    public Object getStockIndexOfSpecifiedCompany(String provider, List<String> index) {
        if (!provider.equals("YAHOO")) {
            logger.warn("Provide the correct provider");
            return notFoundMessage(provider);
        }

        else {

            try {
                logger.info("Converting List to Array");
                String[] strings = new String[index.size()];
                for (int i = 0; i < index.size(); i++) {
                    strings[i] = index.get(i);
                }
                logger.info("Calling Yahoo API with list parameter");
                var stocks = YahooFinance.get(strings);
                logger.info("Getting the Map from  YAHOO API");
                JSONObject jsonObject = new JSONObject();
                Set keySet = stocks.keySet();
                Iterator<String> iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Stock stock = stocks.get(key);
                    logger.info("Iterating through MAP and populating its element to JSON Object");
                    jsonObject.put(stock.getSymbol(), stock.getQuote().getPrice());
                }
                logger.info("Returning the JSON API");
                return jsonObject;
            }
            catch (IOException e){
                logger.error(e.getMessage());
                return YahooFinanceApiService.errorMessage();
            }
        }

    }
}
