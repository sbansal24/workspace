package com.test.pojo;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private JdbcTemplate jdbcTemplate1;
    private JdbcTemplate jdbcTemplate2;

    public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
        this.jdbcTemplate1 = jdbcTemplate1;
    }


    public void setJdbcTemplate2(JdbcTemplate jdbcTemplate2) {
        this.jdbcTemplate2 = jdbcTemplate2;
    }

    public TransactionDaoImpl(JdbcTemplate jdbcTemplate1, JdbcTemplate jdbcTemplate2) {
        this.jdbcTemplate1 = jdbcTemplate1;
        this.jdbcTemplate2 = jdbcTemplate2;
    }


    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = jdbcTemplate1.query("select * from transaction where transaction_id<4", (rs, i) -> {
            Integer transactionId = rs.getInt("transaction_id");
            String transactionName = rs.getString("transaction_name");
            Date transactionDate = rs.getDate("transaction_date");
            Transaction transaction = new Transaction(transactionId, transactionName, transactionDate);
            return transaction;
        });

        List<Transaction> transactions1 = jdbcTemplate2.query("select * from transaction where transaction_id>=4", (rs, i) -> {
            Integer transactionId = rs.getInt("transaction_id");
            String transactionName = rs.getString("transaction_name");
            Date transactionDate = rs.getDate("transaction_date");
            Transaction transaction = new Transaction(transactionId, transactionName, transactionDate);
            return transaction;
        });

        List<Transaction> transactionList = new ArrayList<>(transactions);
        transactionList.addAll(transactions1);
        return transactionList;
    }

    @Override
    public void addMentor() {

    }

    @Override
    public void addUser() {

    }

    @PostConstruct
    public void init(){
        System.out.println("spring dao in post construct method.");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("spring dao in pre destroy method");
    }
}
