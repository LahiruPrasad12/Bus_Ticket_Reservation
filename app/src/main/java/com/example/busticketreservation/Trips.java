package com.example.busticketreservation;

import java.io.Serializable;

public class Trips implements Serializable {
    private String ar_time;
    private String dep_time;
    private String date;
    private String route_id;
    private String trip_id;
    private String Status;
    private Integer noOfSeats;
    private Integer AvailableSeats;
    private float price;

    public Trips() {
    }

    public String getAr_time() {
        return ar_time;
    }

    public void setAr_time(String ar_time) {
        this.ar_time = ar_time;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Integer getAvailableSeats() {
        return AvailableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        AvailableSeats = availableSeats;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
