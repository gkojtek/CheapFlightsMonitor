package com.gkojtek.cfmonitor.cheapflightsmonitor;

import com.gkojtek.cfmonitor.cheapflightsmonitor.model.AllFlights;
import com.gkojtek.cfmonitor.cheapflightsmonitor.model.Flight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JsonHandler {

    protected String getJsonUrl(FlightPreferences flightPreferences) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("https://api.ryanair.com/farefinder/3/oneWayFares?&departureAirportIataCode=");
        stringBuilder.append(flightPreferences.getDepartureAirportIataCode());
        stringBuilder.append("&language=pl-pl&limit=16&market=pl-p&offset=0&outboundDepartureDateFrom=");
        stringBuilder.append(flightPreferences.getDepartureDateFrom());
        stringBuilder.append("&outboundDepartureDateTo=");
        stringBuilder.append(flightPreferences.getDepartureDateTo());
        stringBuilder.append("&priceValueTo=");
        stringBuilder.append(flightPreferences.getMaxPrice());
        String url =  stringBuilder.toString();
        return url;
    }

    protected List getListOfFlights(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        AllFlights allFlights = gson.fromJson(json, AllFlights.class);
        List<Flight> flights = allFlights.getFlights();
        return flights;
    }


}
