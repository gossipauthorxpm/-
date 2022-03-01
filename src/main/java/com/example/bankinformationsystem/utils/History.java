package com.example.bankinformationsystem.utils;

public class History {
    private String sender;
    private String recipient;
    private String sum;
    private String time;

    public History(String sender, String recipient, String sum, String time){
        this.time = time;
        this.sum = sum;
        this.recipient = recipient;
        this.sender = sender;
    }
    public History(){
    }

    public String getSender(){
        return this.sender;
    }
    public String getRecipient(){
        return this.recipient;
    }
    public String getSum(){
        return this.sum;
    }
    public String getTime(){
        return this.time;
    }

}
