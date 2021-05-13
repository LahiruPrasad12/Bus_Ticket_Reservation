package com.example.busticketreservation.TripManager;

import java.io.Serializable;

public class Trip implements Serializable {
    private String trip_id;
    private String route_id;
    private String date;
    private String dep_time;
    private String ar_time;

    public Trip() {
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getRoute_id() {return route_id;}

    public void setRoute_id(String route_id){this.route_id=route_id;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getAr_time() {
        return ar_time;
    }

    public void setAr_time(String ar_time) {
        this.ar_time = ar_time;
    }
}
