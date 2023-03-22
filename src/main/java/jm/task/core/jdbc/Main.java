package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* JDBC
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 21);
        userService.saveUser("Сергей", "Сергеев", (byte) 22);
        userService.saveUser("Владимир", "Владимиров", (byte) 23);
        userService.saveUser("Михаил", "Михайлов", (byte) 24);
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
         */

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Igor", "Belov", (byte) 29);
        userService.saveUser("Ivan", "Mishin", (byte) 22);
        userService.saveUser("Olga", "Semina", (byte) 29);
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
