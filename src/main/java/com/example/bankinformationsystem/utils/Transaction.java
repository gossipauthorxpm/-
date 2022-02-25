package com.example.bankinformationsystem.utils;

import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.DB.ToDatabase;

public class Transaction {

    private String sender_login;
    private String recipient_login;

    public Transaction(String sender_login, String recipient_login){
        this.recipient_login = recipient_login;
        this.sender_login = sender_login;
    }

    public void transaction(int check){
        Integer sender_money =  Integer.parseInt(new FromDatabase().getMoneyPeople(sender_login).trim());
        Integer recipient_money = Integer.parseInt(new FromDatabase().getMoneyPeople(recipient_login).trim());

        TransacitonHander handler = new TransacitonHander(sender_money, recipient_money, check);
        //балансы после перевода
        String sender_balance = toString(handler.returnSenderBalance());
        String recipient_balance = toString(handler.returnRecipientBalance());

        new ToDatabase().updateUserMoney(sender_login, sender_balance);
        new ToDatabase().updateUserMoney(recipient_login, recipient_balance);
    }

    private String toString(int balance) {
        return Integer.toString(balance);
    }
}
