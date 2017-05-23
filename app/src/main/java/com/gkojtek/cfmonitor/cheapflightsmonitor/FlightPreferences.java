package com.gkojtek.cfmonitor.cheapflightsmonitor;

public class FlightPreferences {
    private String departureAirportIataCode;
    private String departureDateFrom;
    private String departureDateTo;
    private int maxPrice;

    public FlightPreferences(String departureAirportIataCode, String departureDateFrom, String departureDateTo, int maxPrice) {
        this.departureAirportIataCode = departureAirportIataCode;
        this.departureDateFrom = departureDateFrom;
        this.departureDateTo = departureDateTo;
        this.maxPrice = maxPrice;
    }

    public String getDepartureAirportIataCode() {
        return departureAirportIataCode;
    }

    public String getDepartureDateFrom() {
        return departureDateFrom;
    }

    public String getDepartureDateTo() {
        return departureDateTo;
    }

    public int getMaxPrice() {
        return maxPrice;
    }
}
