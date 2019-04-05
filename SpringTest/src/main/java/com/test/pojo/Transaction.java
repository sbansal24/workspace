package com.test.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Transaction implements Serializable {
    private Integer transaction_id;
    private String transaction_name;
    private Date transaction_date;

    public Transaction(Integer transaction_id, String transaction_name, Date transaction_date) {
        this.transaction_id = transaction_id;
        this.transaction_name = transaction_name;
        this.transaction_date = transaction_date;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_name() {
        return transaction_name;
    }

    public void setTransaction_name(String transaction_name) {
        this.transaction_name = transaction_name;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transaction_id == that.transaction_id &&
                Objects.equals(transaction_name, that.transaction_name) &&
                Objects.equals(transaction_date, that.transaction_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction_id, transaction_name, transaction_date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", transaction_name='" + transaction_name + '\'' +
                ", transaction_date=" + transaction_date +
                '}';
    }

}
