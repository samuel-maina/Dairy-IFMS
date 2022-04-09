
package com.service;

/**
 *
 * @author samuel
 */


import com.model.Member;
import com.model.MemberPurchases;
import com.model.MilkRecord;
import com.model.Role;
import com.model.ScheduledAccountActivator;
import com.model.User;
import com.model.UserRole;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;


@Component
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/maziwa_x?useSSL=false");
                settings.put(Environment.USER, "samuel");
                settings.put(Environment.PASS, "7924");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "false");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                //settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(MilkRecord.class);
                configuration.addAnnotatedClass(Member.class);
                configuration.addAnnotatedClass(MemberPurchases.class);
                configuration.addAnnotatedClass(ScheduledAccountActivator.class);
                configuration.addAnnotatedClass(UserRole.class);
                configuration.addAnnotatedClass(Role.class);
                

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
