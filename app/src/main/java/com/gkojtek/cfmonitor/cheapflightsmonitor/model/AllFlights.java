
package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllFlights {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("fares")
    @Expose
    private List<Flight> flights = null;
    @SerializedName("arrivalAirportCategories")
    @Expose
    private Object arrivalAirportCategories;
    @SerializedName("size")
    @Expose
    private int size;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFares(List<Flight> fares) {
        this.flights = fares;
    }

    public Object getArrivalAirportCategories() {
        return arrivalAirportCategories;
    }

    public void setArrivalAirportCategories(Object arrivalAirportCategories) {
        this.arrivalAirportCategories = arrivalAirportCategories;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
