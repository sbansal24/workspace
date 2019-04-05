package DesignPatterns.Decorator;

public class HedgeFund extends FundDecorator {
    HedgeFund(Fund fund) {
        super(fund);
    }

    @Override
    public void buildFund(){
        super.buildFund();
        System.out.println("building hedge fund");
    }
}
