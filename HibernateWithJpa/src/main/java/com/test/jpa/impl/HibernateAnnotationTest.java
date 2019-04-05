package com.test.jpa.impl;

import com.test.jpa.entity.Employee;
import com.test.jpa.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateAnnotationTest {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Employee employee = new Employee();
            employee.setFirstName("firstname");
            employee.setLastName("last");
            employee.setEmailId("second@xyz.com");
            Session session = sessionFactory.openSession();
            session.beginTransaction();


            session.save(employee);
            employee.setFirstName("john david");
//            session.clear();

            Employee e1 = (Employee) session.get(Employee.class, 1);
            System.out.println("employee name is " + e1.getFirstName());
            session.getTransaction().commit();
            session.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            Employee employee1 = new Employee();

            employee1.setFirstName("daniel");
            employee1.setLastName("last");
            employee1.setEmailId("find@xyz.com");
            session1.merge(employee1);
            session1.getTransaction().commit();
            System.out.println(employee.getFirstName());
            session1.close();
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }

    }
}
