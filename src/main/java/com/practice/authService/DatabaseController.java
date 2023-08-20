package com.practice.authService;

import com.practice.authService.models.User;

import java.sql.*;

public class DatabaseController {
    private static final String db_url = "jdbc:postgresql://192.168.1.111:5432/plug_db";
    private static final String db_user = "admin";
    private static final String db_password = "admin";
    private static final String table1 = "users";
    private static final String table2 = "emails";

    public User selectByLogin(String login){
        User user = new User();

        try(Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
            Statement statement = connection.createStatement()){

            String query = "select * from " + table1 + " u\n" +
                    "inner join " + table2 + " e \n" +
                    "on u.login = e.login \n" +
                    "where u.login = '" + login + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                user = new User(rs.getString("login"), rs.getString("password"),
                        rs.getString("reg_date"), rs.getString("email"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int insertUserData(User user){
        String query = "insert into " + table1 + "(login, password, reg_date) values(?, ?, ?);"+
                "insert into " + table2 + "(login, email) values(?, ?)";

        int rowsAffected = 0;
        try (Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
             PreparedStatement prepStatement = connection.prepareStatement(query)){

            prepStatement.setString(1, user.getLogin());
            prepStatement.setString(2, user.getPassword());
            prepStatement.setDate(3, Date.valueOf(user.getDate()));
            prepStatement.setString(4, user.getLogin());
            prepStatement.setString(5, user.getEmail());

            rowsAffected = prepStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
