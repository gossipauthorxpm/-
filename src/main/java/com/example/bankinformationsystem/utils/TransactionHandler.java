package com.example.bankinformationsystem.utils;

public class TransactionHandler {

    private int sender_money;
    private int recipient_money;
    private int check;

    public TransactionHandler(int sender_money, int recipient_money, int check){
        this.check = check;
        this.recipient_money = recipient_money;
        this.sender_money = sender_money;
    }

    public int returnSenderBalance(){
        if(validTrans()){
            sender_money -= check;
            return sender_money;
        }
        return -1; //код ошибки перевода денег, на счету недостаточно средств
    }
    public int returnRecipientBalance(){
            recipient_money += check;
            return recipient_money;
    }
    private boolean validTrans(){
        return sender_money >= check ;
    }
}
