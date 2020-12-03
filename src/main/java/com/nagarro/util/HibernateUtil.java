package com.nagarro.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

}
