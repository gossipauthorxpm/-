package com.example.bankinformationsystem.utils;

import java.time.*;

public class HistoryTime {
    private LocalDate date;
    private LocalTime time;

    public HistoryTime(){
         date = LocalDate.now();
         time = LocalTime.now();
    }

    public String getLocalTime(){
        return time.toString();
    }
    public String getLocalDate(){
        return date.toString();
    }
}
