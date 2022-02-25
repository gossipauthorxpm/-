package com.example.bankinformationsystem.utils;

public class TransacitonHander {
    private int sender_money;
    private int recipient_money;
    private int check;

    public TransacitonHander(int sender_money, int recipient_money, int check){
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
        if(validTrans()){
            recipient_money += check;
            return recipient_money;
        }
        return -1; //код ошибки перевода денег, на счету недостаточно средств
    }
    private boolean validTrans(){
        return sender_money >= check;
    }
}
