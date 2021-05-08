package com.example.busticketreservation.BusOwner;

public class Bus {
    private String ID;
    private String RouteNo;
    private String LicenseNo;
    private Integer Seats;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRouteNo() {
        return RouteNo;
    }

    public void setRouteNo(String routeNo) {
        RouteNo = routeNo;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public Integer getSeats() {
        return Seats;
    }

    public void setSeats(Integer seats) {
        Seats = seats;
    }

    public Bus() {


    }
}
