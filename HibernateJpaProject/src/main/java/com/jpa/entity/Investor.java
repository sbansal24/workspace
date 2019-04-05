package com.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investor_id")
    private int investorId;
    private String investorName;

    @ManyToMany(mappedBy = "investors")
    private List<Fund> funds = new ArrayList<>();

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investor investor = (Investor) o;
        return investorId == investor.investorId &&
                Objects.equals(investorName, investor.investorName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(investorId, investorName);
    }
}
