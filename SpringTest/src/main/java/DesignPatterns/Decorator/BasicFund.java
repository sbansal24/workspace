package DesignPatterns.Decorator;

public class BasicFund implements Fund {
    @Override
    public void buildFund() {
        System.out.println("building basic fund");
    }
}
