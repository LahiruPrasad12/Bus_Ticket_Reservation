package com.example.busticketreservation.BusOwner;

public class BusList {
    String BusId,RouteNumbr,LicseNumbr,Numsets;

    public BusList() {
    }

    public BusList(String busId, String routeNumbr, String licseNumbr, String numsets) {
        BusId = busId;
        RouteNumbr = routeNumbr;
        LicseNumbr = licseNumbr;
        Numsets = numsets;
    }

    public String getBusId() {
        return BusId;
    }

    public void setBusId(String busId) {
        BusId = busId;
    }

    public String getRouteNumbr() {
        return RouteNumbr;
    }

    public void setRouteNumbr(String routeNumbr) {
        RouteNumbr = routeNumbr;
    }

    public String getLicseNumbr() {
        return LicseNumbr;
    }

    public void setLicseNumbr(String licseNumbr) {
        LicseNumbr = licseNumbr;
    }

    public String getNumsets() {
        return Numsets;
    }

    public void setNumsets(String numsets) {
        Numsets = numsets;
    }
}
