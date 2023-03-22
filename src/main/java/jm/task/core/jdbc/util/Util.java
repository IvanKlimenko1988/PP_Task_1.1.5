package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Util {
    private final String USER_NAME = "dataTest";
    private final String PASS = "Asdewq123";
    private final String URL = "jdbc:mysql://localhost:3306/users";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection connection;
    private SessionFactory sessionFactory;
    private Configuration configuration;
    private Properties properties;
    private ServiceRegistry serviceRegistry;
    private Session session;

    public Connection getDbConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                configuration = new Configuration();
                properties = new Properties();
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("hibernate.connection.username", USER_NAME);
                properties.setProperty("hibernate.connection.password", PASS);
                properties.setProperty("hibernate.connection.driver-class", DRIVER);
                properties.setProperty("hibernate.current_session_context_class", "thread");
                properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                properties.setProperty("show_sql", "true");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public Session session(SessionFactory factory) {
        session = factory.getCurrentSession();
        return session;
    }
}

