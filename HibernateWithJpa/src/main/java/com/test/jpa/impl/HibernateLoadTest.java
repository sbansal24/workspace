package com.test.jpa.impl;

import com.test.jpa.entity.Employee;
import com.test.jpa.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateLoadTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = (Employee) session.get(Employee.class, 1);

        //lazy loading
        Employee employee1 = (Employee) session.byId(Employee.class).getReference(1);

        System.out.println(employee.getEmailId());

        System.out.println(employee1.getEmailId());
        session.getTransaction().commit();
        HibernateUtil.shutDownSessionFactory();
    }
}
