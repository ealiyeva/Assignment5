package com.example.controller;

import java.util.List;

public interface YahooFinanceController {

    Object getStockIndexByYahooApiInSingleMode(String provider,String index);

    Object getStockIndexByYahooApiInBatchMode(String provider, List<String> index);
}
