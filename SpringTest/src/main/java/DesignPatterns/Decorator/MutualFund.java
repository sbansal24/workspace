package DesignPatterns.Decorator;

public class MutualFund extends FundDecorator {
    MutualFund(Fund fund) {
        super(fund);
    }

    @Override
    public void buildFund(){
        super.buildFund();
        System.out.println("building mutual fund");
    }
}
