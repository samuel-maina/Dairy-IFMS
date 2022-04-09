package com;

import com.model.Role;
import com.model.Roles;
import com.model.User;
import com.model.UserRole;
import com.service.Activator;
import com.service.HibernateUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class Application {
/**
 * Logger setup
 */
    public static Logger logger = Logger.getLogger(Application.class);

    static Properties prop = new Properties();
    static FileReader reader;

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);

            Application.reader = new FileReader("/home/samuel/logger/log4j.properties");
            prop.load(reader);
            PropertyConfigurator.configure(prop);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        Activator.startTimer();
        /**
         * Admin user pre-installation
         */
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setEnabled(true);
        user.setLocked(false);
        user.setPassword(new BCryptPasswordEncoder().encode("7924"));
        user.setUserId("ADMIN");
        User x = session.find(User.class, "ADMIN");
        if (x == null) {
            session.save(user);
        }
        /*
        Pre-intallation of all roles/permissions
         */
        Roles[] c = Roles.values();
        for (Roles roles : c) {
            Role rolex = new Role();
            if (roles.name().equals("user_management")) {
                rolex.setAlias("User Management");
                rolex.setDescription("Create user and grant them permissions");
            }
            if (roles.name().equals("milk_management")) {
                rolex.setAlias("Milk Management");
                rolex.setDescription("Perform all milk operations");
            }

            if (roles.name().equals("member_management")) {
                rolex.setAlias("Member Management");
                rolex.setDescription("Perform all member operations");
            }

            if (roles.name().equals("store_management")) {
                rolex.setAlias("Store Management");
                rolex.setDescription("perform all store operations");
            }
            if (roles.name().equals("deductions_management")) {
                rolex.setAlias("Deductions Management");
                rolex.setDescription("Perform all deductions operations");
            }
            if (roles.name().equals("system_admin")) {
                rolex.setAlias("System administrator");
                rolex.setDescription("Perform all system operations");
            }

            rolex.setRoleType(roles);
            rolex.setId(roles.toString());

            if (session.find(Role.class, roles.toString()) != null) {

            } else {
                session.save(rolex);

            }
        }
        UserRole userRole = new UserRole();
        userRole.setRoles(session.find(Role.class, Roles.user_management.name()));
        userRole.setUser(user);
        session.save(userRole);
        transaction.commit();

    }

}
