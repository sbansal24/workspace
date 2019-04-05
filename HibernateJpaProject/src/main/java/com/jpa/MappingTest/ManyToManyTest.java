package com.jpa.MappingTest;

import com.jpa.Util.HibernateUtil;
import com.jpa.entity.Fund;
import com.jpa.entity.Investor;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManyToManyTest {
    public static void main(String[] args) {
        Fund fund1 = new Fund();
        fund1.setFundName("fund1");
        Fund fund2 = new Fund();
        fund2.setFundName("fund2");

        Investor investor1 = new Investor();
        investor1.setInvestorName("investor1");
        Investor investor2 = new Investor();
        investor2.setInvestorName("investor2");
        Investor investor3 = new Investor();
        investor3.setInvestorName("investor3");

        fund1.getInvestors().add(investor1);
        fund1.getInvestors().add(investor2);

        fund2.getInvestors().add(investor1);
        fund2.getInvestors().add(investor3);

        Session session = null;
        try {
            session = HibernateUtil.getSessionfactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(fund1);
            session.save(fund2);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            HibernateUtil.shutDownSessionFactory();
        }

    }
}
