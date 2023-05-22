package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Name1", "lastName1", (byte) 1);
        us.saveUser("Name2", "lastName2", (byte) 2);
        us.saveUser("Name3", "lastName3", (byte) 3);
        us.saveUser("Name4", "lastName4", (byte) 4);
        List<User> userList = us.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
