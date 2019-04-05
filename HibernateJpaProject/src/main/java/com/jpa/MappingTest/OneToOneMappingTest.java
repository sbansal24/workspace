package com.jpa.MappingTest;

import com.jpa.Util.HibernateUtil;
import com.jpa.entity.Address;
import com.jpa.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OneToOneMappingTest {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("john");
        student.setLastName("cameron");
        student.setSection("B");

        Address address = new Address();
        address.setCity("delhi");
        address.setCountry("india");
        student.setAddress(address);
        address.setStreet("spectrum drive");
        Session session = null;
        try {
            session = HibernateUtil.getSessionfactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.persist(student);
            transaction.commit();
            List<Student> studentList = session.createQuery("from Student").list();
            for(Student student1 : studentList){
                System.out.println(student1.getAddress());
            }

        } finally {
            if (session != null)
                session.close();
            HibernateUtil.shutDownSessionFactory();
        }

    }
}
