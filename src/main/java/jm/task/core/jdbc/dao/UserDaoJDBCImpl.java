package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util db = new Util();
    private PreparedStatement prepStatement;

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users"
            + "(id INT PRIMARY KEY AUTO_INCREMENT,"
            + "userName VARCHAR(20) NOT NULL,"
            + "lastName VARCHAR(20) NOT NULL,"
            + "age INT NOT NULL)";

    private final String ADD = "INSERT users(userName, lastName, age)"
            + "VALUES (?, ?, ?)";

    private final String DROP_TABLE = "DROP TABLE IF EXISTS users";

    private final String USER_DEL = "DELETE FROM users WHERE Id = ";

    private final String ALL = "SELECT * FROM users";

    private final String CLEAR = "TRUNCATE TABLE users";

    public void createUsersTable() {
        try (Connection connection = db.getDbConnection()) {
            prepStatement = connection.prepareStatement(CREATE_TABLE);
            prepStatement.executeUpdate();
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = db.getDbConnection()) {
            prepStatement = connection.prepareStatement(DROP_TABLE);
            prepStatement.executeUpdate();
            System.out.println("Drop table users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = db.getDbConnection()) {
            prepStatement = connection.prepareStatement(ADD);
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setInt(3, age);
            prepStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = db.getDbConnection()) {
            prepStatement = connection.prepareStatement(USER_DEL + id);
            prepStatement.executeUpdate();
            System.out.println("User id " + id + " deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = db.getDbConnection()) {
            List<User> userList = new ArrayList<>();
            prepStatement = connection.prepareStatement(ALL);
            ResultSet resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try (Connection connection = db.getDbConnection()) {
            prepStatement = connection.prepareStatement(CLEAR);
            prepStatement.executeUpdate();
            System.out.println("Table users clean");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
