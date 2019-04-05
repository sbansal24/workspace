package com.test.pojo;

import java.util.List;

public interface TransactionDao {

    List<Transaction> getTransactions();

    void addMentor();

    void addUser();

}
