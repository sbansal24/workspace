package com.springboot.microservice.fx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Service
public class ForexController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @Transactional(value = Transactional.TxType.REQUIRED)
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }

    @GetMapping("/currency-exchange/get")
    public List<ExchangeValue> getExchangeValues(){
        return exchangeValueRepository.findAll();
    }

    @GetMapping("/transactions/get")
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
