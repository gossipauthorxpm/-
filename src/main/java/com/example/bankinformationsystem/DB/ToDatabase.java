package com.example.bankinformationsystem.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToDatabase extends Database {
    //
    public void updateCurrentUser(User user){
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("UPDATE users SET password=?, realname=?, status=?, money=? WHERE(card="+user.getCard()+")");

            sql_request.setString(1, user.getPassword());
            sql_request.setString(2, user.getRealName());
            sql_request.setString(3, user.getStatus());
            sql_request.setString(4, user.getMoney());

            sql_request.execute();
        }catch (SQLException e){
            System.out.println("Ошибка SQL " + e);
        }
    }
    //
    public void addNewUser(User new_user){
        addNewUserInAuthorization(new_user);
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?)");
            sql_request.setString(1, new_user.getRealName());
            sql_request.setString(2, new_user.getStatus());
            sql_request.setString(3, new_user.getLogin());
            sql_request.setString(4, new_user.getMoney());
            sql_request.setString(5, new_user.getCard());
            sql_request.setString(6, new_user.getPassword());

            sql_request.execute();
        }catch (SQLException e){
            System.out.println("Ошибка SQL" + e);
        }
    }
    public void updateUserMoney(String login, String money){
        Connection database = getDatabaseConnection();
        try{
            PreparedStatement sql_request = database.prepareStatement("UPDATE users SET money=? WHERE login=?");
            sql_request.setString(1, money);
            sql_request.setString(2, login);

            sql_request.execute();

        } catch (Exception e){
            System.out.println("SQL ERROR : " + e);
        }
    }
    public void updateHistory(String local_date, String local_time, String from_user, String to_user, String sum){
        Connection database = getDatabaseConnection();
        try{
            PreparedStatement sql_request = database.prepareStatement("INSERT INTO transaction VALUES(?,?,?,?)");
            sql_request.setString(1,to_user);
            sql_request.setString(2, from_user);
            sql_request.setString(3, sum);
            sql_request.setString(4, local_date + " " + local_time);
            sql_request.execute();
        }catch (Exception e){
            System.out.println("SQL ERROR : " + e);
        }
    }
    private void addNewUserInAuthorization(User new_user){
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("INSERT INTO authorization VALUES(?,?,?)");
            sql_request.setString(1, new_user.getLogin());
            sql_request.setString(2, new_user.getPassword());
            sql_request.setString(3, "user");

            sql_request.execute();

        }catch (SQLException e ){
            System.out.println("Ошибка SQL " + e);
        }
    }
}
