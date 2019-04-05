package DesignPatterns.Decorator;

public class FundDecorator implements Fund {
    Fund fund;

    FundDecorator(Fund fund){
        this.fund = fund;
    }

    @Override
    public void buildFund() {
        fund.buildFund();
    }
}
