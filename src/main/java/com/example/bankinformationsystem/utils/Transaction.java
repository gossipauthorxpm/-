package com.example.bankinformationsystem.utils;

import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.DB.ToDatabase;

public class Transaction {

    private final String SENDER_LOGIN;
    private final String RECIPIENT_LOGIN;
    private String sender_balance;
    private String recipient_balance;


    public Transaction(String sender_login, String recipient_login){
        this.RECIPIENT_LOGIN = recipient_login;
        this.SENDER_LOGIN = sender_login;
    }

    public void transaction(int check) throws Exception{
        int sender_money =  Integer.parseInt(new FromDatabase().getMoneyPeople(SENDER_LOGIN).trim());
        int recipient_money = Integer.parseInt(new FromDatabase().getMoneyPeople(RECIPIENT_LOGIN).trim());

        TransactionHandler handler = new TransactionHandler(sender_money, recipient_money, check);
        //балансы после перевода
        sender_balance = toString(handler.returnSenderBalance());
        recipient_balance = toString(handler.returnRecipientBalance());

        if(sender_balance.equals("-1") || recipient_balance.equals("-1")){
            throw new Exception("Ошибка перевода!");
        }

        new ToDatabase().updateUserMoney(SENDER_LOGIN, sender_balance);
        new ToDatabase().updateUserMoney(RECIPIENT_LOGIN, recipient_balance);
    }

    public boolean validTransaction(){
        return !sender_balance.equals("-1") || !recipient_balance.equals("-1");
    }
    private String toString(int balance) {
        return Integer.toString(balance);
    }
}
