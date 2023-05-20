package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users"
                + "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL)");
        } catch (SQLException e) {
            System.out.println("createUsersTable ERROR");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("dropUsersTable ERROR");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)")){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("saveUser ERROR");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("removeUserById ERROR");
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("getAllUsers ERROR");
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("cleanUsersTable ERROR");
        }
    }
}
