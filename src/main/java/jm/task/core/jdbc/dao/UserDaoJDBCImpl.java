package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            String createDataBase = "CREATE DATABASE IF NOT EXISTS users";
            String useDB = "USE users";
            String createTable = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name VARCHAR(20) NOT NULL,\n" +
                    "    lastName VARCHAR(20) NOT NULL,\n" +
                    "    age INT NOT NULL)";
            statement.executeUpdate(createDataBase);
            statement.executeUpdate(useDB);
            statement.executeUpdate(createTable);
            System.out.println("Database has been created!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Drop table users");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            String add = "INSERT users(name, lastName, age) VALUES ('"
                    + name + "','" + lastName + "','" + age + "')";
            statement.executeUpdate(add);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE Id = " + id);
            System.out.println("User id " + id + " deleted");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            List<User> userList = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                userList.add(new User(name, lastName, age));
            }
            return userList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        Util db = new Util();
        try (Connection connection = db.getDbConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users");
            System.out.println("Table users clean");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
