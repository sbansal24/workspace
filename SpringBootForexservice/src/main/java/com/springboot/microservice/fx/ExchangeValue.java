package com.springboot.microservice.fx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "exchangevalue")
public class ExchangeValue {
    @Id
    private Integer id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    @Column(name = "conversion_multiple")
    private Integer conversionMultiple;
    private int port;

    public ExchangeValue() {
    }

    public ExchangeValue(Integer id, String from, String to, Integer conversionMultiple) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(Integer conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeValue that = (ExchangeValue) o;
        return id == that.id &&
                port == that.port &&
                Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(conversionMultiple, that.conversionMultiple);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, conversionMultiple, port);
    }
}
