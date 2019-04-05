package com.test.pojo;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    void multiDataSourceTest();
}
