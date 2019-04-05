package com.test.springboot.microservice.currencyconversion;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient("forex-service")
@FeignClient("netflix-zuul-api-gateway-server")
@RibbonClient("forex-service")
public interface CurrencyExchangeServiceProxy {
//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
@GetMapping("forex-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retreiveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
