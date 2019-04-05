package com.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_id")
    private int fundId;
    private String fundName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "fund_investor", joinColumns = {@JoinColumn(name = "fund_id")},
            inverseJoinColumns = {@JoinColumn(name = "investor_id")})
    private List<Investor> investors = new ArrayList<>();

    public List<Investor> getInvestors() {
        return investors;
    }

    public void setInvestors(List<Investor> investors) {
        this.investors = investors;
    }

    public int getFundId() {
        return fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fund fund = (Fund) o;
        return fundId == fund.fundId &&
                Objects.equals(fundName, fund.fundName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fundId, fundName);
    }
}
