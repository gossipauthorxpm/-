package com.example.bankinformationsystem.DB;

import com.example.bankinformationsystem.utils.Encoder;

public class User {

    private String login;
    private String password;
    private String real_name;
    private String status;
    private String money;
    private String card;

    public User(String login, String password, String real_name, String status, String money, String card){
        this.login = login;
        this.password = Encoder.encrypt(password);
        this.real_name = real_name;
        this.status = status;
        this.money = money;
        this.card = card;
    }

    public String getLogin(){return login;}
    public String getPassword(){return password;}
    public String getStatus(){return status;}
    public String getMoney(){return money;}
    public String getCard(){return card;}
    public String getRealName(){return real_name;}

}
