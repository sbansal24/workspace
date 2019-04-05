package com.test.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    @Autowired
    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public TransactionServiceImpl(){

    }

    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }


    @Override
    @Transactional
    public List<Transaction> getTransactions() {
        return transactionDao.getTransactions();
    }

    @Override
    @Transactional
    public void multiDataSourceTest() {
        transactionDao.addMentor();
        transactionDao.addUser();
        throw new RuntimeException("rollingBack");
    }

    @PostConstruct
    public void init(){
        System.out.println("spring in post construct method.");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("spring in pre destroy method");
    }
}
