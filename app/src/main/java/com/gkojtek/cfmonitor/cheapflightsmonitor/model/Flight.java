package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("outbound")
    @Expose
    private FlightDetails flightDetails;
    @SerializedName("summary")
    @Expose
    private FlightSummary flightSummary;

    public FlightDetails getDetails() {
        return flightDetails;
    }

    public void setFlightDetails(FlightDetails flightDetails) {
        this.flightDetails = flightDetails;
    }

    public FlightSummary getSummary() {
        return flightSummary;
    }

    public void setSummary(FlightSummary flightSummary) {
        this.flightSummary = flightSummary;
    }

}
