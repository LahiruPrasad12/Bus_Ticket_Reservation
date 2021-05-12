package com.example.busticketreservation;

import java.io.Serializable;

public class addRout implements Serializable {
    private String Bus_No;
    private String From;
    private String To;
    private String Arrival_Time;
    private String Departure_Time;
    private String Price;

    public addRout() {
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

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(String arrival_Time) {
        Arrival_Time = arrival_Time;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String departure_Time) {
        Departure_Time = departure_Time;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
