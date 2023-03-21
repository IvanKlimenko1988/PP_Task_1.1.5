package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String USER_NAME = "dataTest";
    private final String PASS = "Asdewq123";
    private final String URL = "jdbc:mysql://localhost:3306/users";

    private Connection connection;

    public Connection getDbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
