package com.example.bankinformationsystem.utils;

import com.example.bankinformationsystem.DB.FromDatabase;
import com.example.bankinformationsystem.DB.ToDatabase;

public class Transaction {

    private final String SENDER_LOGIN;
    private final String RECIPIENT_LOGIN;

    public Transaction(String sender_login, String recipient_login){
        this.RECIPIENT_LOGIN = recipient_login;
        this.SENDER_LOGIN = sender_login;
    }

    public void transaction(int check) throws UserException {
        String sender_status = new FromDatabase().getStatusUser(SENDER_LOGIN);
        String recipient_status = new FromDatabase().getStatusUser(RECIPIENT_LOGIN);

        if(sender_status.equals("red") || recipient_status.equals("red")){
            throw new UserException("Перевод невозможен! Один из аккаунтов заблокирован!");
        }
        if(check <= 0){
            throw new UserException("Введите положительное число!");
        }
        //балансы до перевода
        int sender_balance_before_transaction =  Integer.parseInt(new FromDatabase().getMoneyPeople(SENDER_LOGIN).trim());
        int recipient_money_before_transaction = Integer.parseInt(new FromDatabase().getMoneyPeople(RECIPIENT_LOGIN).trim());

        TransactionHandler handler = new TransactionHandler(sender_balance_before_transaction, recipient_money_before_transaction, check);
        //балансы после перевода
        String sender_balance_after_transaction = toString(handler.returnSenderBalance());
        String recipient_balance_after_transaction = toString(handler.returnRecipientBalance());

        if(sender_balance_after_transaction.equals("-1") || recipient_balance_after_transaction.equals("-1")){
            throw new UserException("Ошибка перевода!");
        }

        new ToDatabase().updateUserMoney(SENDER_LOGIN, sender_balance_after_transaction);
        new ToDatabase().updateUserMoney(RECIPIENT_LOGIN, recipient_balance_after_transaction);
    }

    private String toString(int balance) {
        return Integer.toString(balance);
    }
}
