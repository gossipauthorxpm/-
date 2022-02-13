package com.example.bankinformationsystem.DB;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    //для авторизации
    private List<ArrayList<String>> authorize_data;

    public DataHandler(List<ArrayList<String>> authorize_data){
        this.authorize_data = authorize_data;
    }
    //user - обычный пользователь, admin - админ
    /*Нулевой индекс - логины, Первый индекс - пароли, Второй индекс - Роль в системе(Админ, пользователь)*/
    //Проверка на обычного пользователя
    public boolean validAuthorize(String login, String password){
        Object[] logins = authorize_data.get(0).toArray();
        Object[] passwords = authorize_data.get(1).toArray();

        for(int i = 0; i < logins.length; i++){
            if(logins[i].toString().equals(login)){
                if(passwords[i].toString().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    //проверка на пользователя админа
    public boolean validAdmin(String login, String password){
        Object[] logins = authorize_data.get(0).toArray();
        Object[] passwords = authorize_data.get(1).toArray();
        Object[] admins = authorize_data.get(2).toArray();

        for(int i = 0; i < logins.length; i++){
            if(logins[i].toString().equals(login)){
                if(passwords[i].toString().equals(password)){
                    if(admins[i].toString().equals("admin")){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //проверка на присутствие карты в бд
    public static boolean cardInBD(String card, ObservableList<String> list_card_db){
        for (String item : list_card_db) {
            if(item.equals(card)){return true;}
        }
        return false;
    }
    //проверка на присутствие логина в бд
    public static boolean loginInBD(String login, List<String> logins){
        for (String item : logins) {
            if(item.equals(login)){return true;}
        }
        return false;
    }
}
