package com.example.busticketreservation;

import java.io.Serializable;

public class Final_Bill implements Serializable {

    private String User_Id ;
    private String Bus_No ;
    private String From;
    private String To;
    private int TotAmount;
    private int Num_Of_Seats;

    public Final_Bill() {
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getBus_No() {
        return Bus_No;
    }

    public void setBus_No(String bus_No) {
        Bus_No = bus_No;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public int getTotAmount() {
        return TotAmount;
    }

    public void setTotAmount(int totAmount) {
        TotAmount = totAmount;
    }

    public int getNum_Of_Seats() {
        return Num_Of_Seats;
    }

    public void setNum_Of_Seats(int num_Of_Seats) {
        Num_Of_Seats = num_Of_Seats;
    }
}
