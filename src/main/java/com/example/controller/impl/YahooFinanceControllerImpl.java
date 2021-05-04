package com.example.controller.impl;

import com.example.controller.YahooFinanceController;
import com.example.service.YahooFinanceApiService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/stock")
public class YahooFinanceControllerImpl implements YahooFinanceController {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceControllerImpl.class);

    @Inject
    private YahooFinanceApiService apiService;

    @Override
    @Get("/singleMode/")
    public Object getStockIndexByYahooApiInSingleMode(@QueryValue String provider, @QueryValue String index) {
        logger.info("Calling the YahooService in singleMode");
        return apiService.getStockIndexOfSpecifiedCompany(provider,index);
    }

    @Override
    @Get("/batchMode")
    public Object getStockIndexByYahooApiInBatchMode(@QueryValue String provider, @QueryValue List<String> index) {
        logger.info("Calling the YahooService in batchMode");
        return apiService.getStockIndexOfSpecifiedCompany(provider,index);
    }


}
