package com.example.bankinformationsystem.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

public class DatabaseHandler {

    public Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
        } catch (SQLException e) {
            System.out.println("FAILED" + e);
            return null;
        }
    }

    public List<ArrayList<String>> getAuthorizeDate() {
        Connection database = getDatabaseConnection();
        List<ArrayList<String>> data = new ArrayList<>();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT * FROM authorization");
            ResultSet sql_result = sql_request.executeQuery();
            ArrayList<String> logins = new ArrayList<>();
            ArrayList<String> passwords = new ArrayList<>();
            ArrayList<String> roles = new ArrayList<>();
            while(sql_result.next()){
                logins.add(sql_result.getString("login"));
                passwords.add(sql_result.getString("password"));
                roles.add(sql_result.getString("role"));
            }
            data.add(logins);
            data.add(passwords);
            data.add(roles);
            return data;
        }catch (SQLException e){
            System.out.println("Ошибка SQL" + e);
            return null;
        }
    }
    public ObservableList<String> getAllCards(){
        ArrayList<String> cards = new ArrayList<>();
        Connection database = getDatabaseConnection();
        try{
            PreparedStatement sql_request = database.prepareStatement("SELECT card FROM users");
            ResultSet sql_result = sql_request.executeQuery();
            while(sql_result.next()){
                cards.add(sql_result.getString("card"));
            }
            ObservableList<String> table_cards = FXCollections.observableArrayList();
            Object[] cards_list = cards.toArray();
            for (Object item : cards_list) {
                table_cards.add(item.toString());
            }
            return table_cards;
        }catch (SQLException e){
            System.out.println("Ошибка SQL" + e);
            return null;
        }
    }
    public List<String> getInfoFromCard(String card){
        List<String> info_from_card = new ArrayList<>();
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT realname, status, login, password, money FROM users WHERE(card="+card+")");
            ResultSet sql_result = sql_request.executeQuery();
            while (sql_result.next()){
                info_from_card.add(sql_result.getString("realname"));
                info_from_card.add(sql_result.getString("status"));
                info_from_card.add(sql_result.getString("login"));
                info_from_card.add(sql_result.getString("password"));
                info_from_card.add(sql_result.getString("money"));
            }
            return info_from_card;
        }catch (SQLException e ){
            System.out.println("Ошибка SQL" + e);
            return null;
        }
    }
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
    public List<String> getListLogins(){
        Connection database = getDatabaseConnection();
        List<String> logins = new ArrayList<>();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT login FROM authorization;");
            ResultSet sql_result = sql_request.executeQuery();
            while (sql_result.next()){
                logins.add(sql_result.getString("login"));
            }
            return logins;
        }catch (SQLException e){
            System.out.println("Ошибка SQL" + e);
            return null;
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

