package com.example.busticketreservation;

import java.io.Serializable;

public class userBookingHistry implements Serializable {

    private String userId;
    private String tripId;
    private String routeId;
    private Integer numOfseats;
    private Integer ToatlAmount;
    private String status="pending";

    public userBookingHistry() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Integer getNumOfseats() {
        return numOfseats;
    }

    public void setNumOfseats(Integer numOfseats) {
        this.numOfseats = numOfseats;
    }

    public Integer getToatlAmount() {
        return ToatlAmount;
    }

    public void setToatlAmount(Integer toatlAmount) {
        ToatlAmount = toatlAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
