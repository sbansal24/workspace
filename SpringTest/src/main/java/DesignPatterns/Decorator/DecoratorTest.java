package DesignPatterns.Decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Fund fund = new BasicFund();
        Fund hedgeFund = new HedgeFund(fund);
        hedgeFund.buildFund();
        Fund mutualFund = new MutualFund(fund);
        mutualFund.buildFund();

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.remove(1);
//        System.out.println(list);
    }
}
