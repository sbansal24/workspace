package com.jpa.MappingTest;

import com.jpa.Util.HibernateUtil;
import com.jpa.entity.Employee;
import com.jpa.entity.Mentor;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ManyToOneMappingTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee();
        employee1.setEmployeeName("employee1");
        Employee employee2 = new Employee();
        employee2.setEmployeeName("employee2");
        Employee employee3 = new Employee();
        employee3.setEmployeeName("employee3");
        Employee employee4 = new Employee();
        employee4.setEmployeeName("employee4");

        Mentor mentor1 = new Mentor();
        mentor1.setMentorName("mentor1");
        Mentor mentor2 = new Mentor();
        mentor2.setMentorName("mentor2");

        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        employee1.setMentor(mentor1);
        employee2.setMentor(mentor2);
        employee3.setMentor(mentor1);
        employee4.setMentor(mentor2);

        employeeList1.add(employee1);
        employeeList2.add(employee2);
        employeeList1.add(employee3);
        employeeList2.add(employee4);

        mentor1.setEmployees(employeeList1);
        mentor2.setEmployees(employeeList2);


        Session session = null;
        try {
            session = HibernateUtil.getSessionfactory().openSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
//            session.persist(mentor1);
//            session.persist(mentor2);
            //query by restriction
//            List<Employee> employees = session.createCriteria(Employee.class)
//                    .add(Restrictions.eq("employeeName", "employee1")).list(); //session.createQuery("from Employee").list();
//query by fetch mode
            Criteria criteria = session.createCriteria(Employee.class);
            criteria.add((Restrictions.gt("employeeId", 1)));
            criteria.setFetchMode("mentor", FetchMode.JOIN);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            List<Employee> employees= criteria.list();
            for(Employee employee : employees){
                System.out.println(employee.getEmployeeName());
                System.out.println(employee.getMentor().getMentorName());
            }
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
