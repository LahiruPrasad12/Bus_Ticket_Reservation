package com.example.busticketreservation.TripManager;

public class TripsModelClass {

    private int trip_id;
    private String trip_date;
    private String arrival_time;
    private String depature_time;
    private String bus_id;

    //constructor
   TripsModelClass(int trip_id, String trip_date, String arrival_time, String depature_time, String bus_id) {
        this.trip_id = trip_id;
        this.trip_date = trip_date;
        this.arrival_time = arrival_time;
        this.depature_time = depature_time;
        this.bus_id = bus_id;
    }

    //getters
    public int getTrip_id() {
        return trip_id;
    }

    public String getTrip_date() {
        return trip_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getDepature_time() {
        return depature_time;
    }

    public String getBus_id() {
        return bus_id;
    }
}
