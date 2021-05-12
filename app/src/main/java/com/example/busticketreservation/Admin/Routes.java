package com.example.busticketreservation.Admin;

import java.io.Serializable;

public class Routes implements Serializable {

    private String routeNo;
    private String from;
    private String to;
    private Integer noOfStops;
    private Integer basePrice;
    private Integer stopPrice;
    private int fullRoutePrice;

    public Routes() {

    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getNoOfStops() {
        return noOfStops;
    }

    public void setNoOfStops(Integer noOfStops) {
        this.noOfStops = noOfStops;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(Integer stopPrice) {
        this.stopPrice = stopPrice;
    }

    public int getFullRoutePrice() {
        return fullRoutePrice;
    }

    public void setFullRoutePrice(int fullRoutePrice) {
        this.fullRoutePrice = fullRoutePrice;
    }
}
