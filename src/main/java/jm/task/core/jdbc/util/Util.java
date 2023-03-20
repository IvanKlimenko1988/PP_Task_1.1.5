package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String userName = "dataTest";
    private final String password = "Asdewq123";
    private final String connectionURL = "jdbc:mysql://localhost:3306/users";

    private Connection connection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException, NullPointerException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }
}
