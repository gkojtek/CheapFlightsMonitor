
package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightDetails {

    @SerializedName("departureAirport")
    @Expose
    private DepartureAirport departureAirport;
    @SerializedName("arrivalAirport")
    @Expose
    private ArrivalAirport arrivalAirport;
    @SerializedName("departureDate")
    @Expose
    private String departureDate;
    @SerializedName("arrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("price")
    @Expose
    private Price price;

    public DepartureAirport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(DepartureAirport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public ArrivalAirport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(ArrivalAirport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

}
