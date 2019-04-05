package com.test.jpa.impl;

import com.test.jpa.entity.DepartmentEntity;
import com.test.jpa.util.HibernateUtil;
import org.hibernate.Session;

public class SecondLevelCacheImpl {
    public static void main(String[] args) {
        storeDate();

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            DepartmentEntity departmentEntity = (DepartmentEntity) session.load(DepartmentEntity.class, 1);
            System.out.println("department name is "+departmentEntity.getName());
            session.getTransaction().commit();
            session.close();
        } finally {
            HibernateUtil.shutDownSessionFactory();
        }
    }

    private static void storeDate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("department one");
        session.save(departmentEntity);
        session.getTransaction().commit();
        session.close();


    }
}
