package com.test.jpa.impl;

import com.test.jpa.entity.Contact;
import com.test.jpa.entity.Investor;
import com.test.jpa.util.HibernateUtil;
import org.hibernate.Session;

public class HiberanateEmbeddableTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Contact contact = new Contact();
        contact.setContactId(1);
        contact.setName("contact1");

        Investor investor = new Investor();
        investor.setInvestorName("investor1");
        investor.setContact(contact);

        session.save(investor);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutDownSessionFactory();
    }
}
