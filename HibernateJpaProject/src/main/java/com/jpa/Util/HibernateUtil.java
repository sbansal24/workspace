package com.jpa.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.io.File;


public class HibernateUtil {
    private HibernateUtil() {

    }

    private static volatile SessionFactory sessionFactory = null;

    public static SessionFactory getSessionfactory() {
        return sessionFactory();
    }

    private static SessionFactory sessionFactory() {
        if (sessionFactory == null) {
            synchronized (HibernateUtil.class) {
                if (sessionFactory == null) {
                    sessionFactory = new AnnotationConfiguration()
                            .configure(new File("src/main/resources/persistance.xml"))
                            .buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }

    public static void shutDownSessionFactory() {
        sessionFactory.close();
    }
}
