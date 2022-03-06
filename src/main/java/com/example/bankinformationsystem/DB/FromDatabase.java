package com.example.bankinformationsystem.DB;

import com.example.bankinformationsystem.utils.Encoder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FromDatabase extends Database{

    public List<ArrayList<String>> getAuthorizeData() {
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
                passwords.add(Encoder.decrypt(sql_result.getString("password")));
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
                info_from_card.add(Encoder.decrypt(sql_result.getString("password")));
                info_from_card.add(sql_result.getString("money"));
            }
            return info_from_card;
        }catch (SQLException e ){
            System.out.println("Ошибка SQL" + e);
            return null;
        }
    }
    public List<String> getAllLogins(){
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
    public ArrayList<String> getInfoToUserUI(String login){
        Connection database = getDatabaseConnection();
        ArrayList<String> data = new ArrayList<>();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT money, card FROM users WHERE login=?");
            sql_request.setString(1, login);
            ResultSet sql_result = sql_request.executeQuery();
            while (sql_result.next()){
                data.add(sql_result.getString("money"));
                data.add(sql_result.getString("card"));
            }
            return data;
        }catch (Exception e){
            System.out.println("Fail : " + e);
            return null;
        }
    }
    public String getMoneyPeople(String sender_login){
        String money = null;
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT money FROM users WHERE login=?");
            sql_request.setString(1, sender_login);
            ResultSet sql_result = sql_request.executeQuery();
            while (sql_result.next()){
                money = sql_result.getString("money");
            }
            return money;
        }catch (Exception e){
            System.out.println("Ошибка SQL : " + e);
            return null;
        }
    }
    public String getLoginForCard(String card){
        String login = null;
        Connection database = getDatabaseConnection();
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT login FROM users WHERE card=?");
            sql_request.setString(1, card);
            ResultSet sql_result = sql_request.executeQuery();
            while (sql_result.next()){
                login = sql_result.getString("login");
            }
            return login;
        }catch (Exception e){
            System.out.println("SQL ERROR : " + e);
            return null;
        }
    }
    public ObservableList<String[]> getHistoryUser(String user){
        Connection database = getDatabaseConnection();
        ObservableList<String[]> full_list_history = FXCollections.observableArrayList();
        try{
            PreparedStatement sql_request = database.prepareStatement("SELECT * FROM transaction WHERE from_user = ?");
            sql_request.setString(1, user);
            ResultSet sql_result = sql_request.executeQuery();

            while (sql_result.next()){
                full_list_history.add(new String[]{sql_result.getString("to_user"), sql_result.getString("from_user"),
                    sql_result.getString("sum"), sql_result.getString("time")});
            }

            return full_list_history;
        }catch (Exception e){
            System.out.println("SQL ERROR : " + e);
            return null;
        }
    }
    public String getStatusUser(String user){
        Connection database = getDatabaseConnection();
        String status = null;
        try {
            PreparedStatement sql_request = database.prepareStatement("SELECT status FROM users WHERE login=?");
            sql_request.setString(1, user);
            ResultSet sql_result = sql_request.executeQuery();
            while(sql_result.next()){
                status = sql_result.getString("status");
            }
            return status;
        }catch (Exception e){
            System.out.println("ERROR SQL : " + e);
            return null;
        }
    }
}
