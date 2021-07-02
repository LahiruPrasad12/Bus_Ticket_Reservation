package com.example.busticketreservation;

import java.io.Serializable;

public class SavedPlace implements Serializable {

    private String name;
    private String RouteNumber;
    private String userId;

    public SavedPlace() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteNumber() {
        return RouteNumber;
    }

    public void setRouteNumber(String routeNumber) {
        RouteNumber = routeNumber;
    }
}
