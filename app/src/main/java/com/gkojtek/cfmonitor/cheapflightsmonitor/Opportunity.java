package com.gkojtek.cfmonitor.cheapflightsmonitor;

import com.gkojtek.cfmonitor.cheapflightsmonitor.model.Flight;

import java.util.HashMap;
import java.util.Map;

public class Opportunity implements Comparable<Opportunity> {

    Map<String, Double> currencies = new HashMap<String, Double>();


    private String arrivalAirportOutbound;
    private String arrivalAirportIataOutbound;
    private String departureDateOutbound;
    private String arrivalDateOutbound;
    private double priceOutbound;
    private String currencyOutbound;
    private String departureCountryInbound;
    private String departureAirportInbound;
    private String departureAirportIataInbound;
    private String arrivalCountryInbound;
    private String arrivalAirportInbound;
    private String arrivalAirportIataInbound;
    private String departureDateInbound;
    private String arrivalDateInbound;
    private double priceInbound;
    private String currencyInbound;
    private double priceTotal;
    private boolean directConnection;
    private boolean goodDeal;
    private Flight inbound;
    private Flight outbound;
    private String departureCountryOutbound;
    private String departureAirportOutbound;
    private String departureAirportIataOutbound;
    private String arrivalCountryOutbound;

    public Opportunity(Flight inbound, Flight outbound) {
        this.inbound = inbound;
        this.outbound = outbound;

        currencies.put("AUD", 2.80);
        currencies.put("BRL", 1.15);
        currencies.put("CAD", 2.78);
        currencies.put("CHF", 3.85);
        currencies.put("CZK", 0.16);
        currencies.put("DKK", 0.57);
        currencies.put("EUR", 4.20);
        currencies.put("GBP", 4.88);
        currencies.put("HUF", 0.02);
        currencies.put("LTL", 1.23);
        currencies.put("LVL", 6.04);
        currencies.put("MAD", 0.39);
        currencies.put("NOK", 0.45);
        currencies.put("PLN", 1.00);
        currencies.put("RUB", 0.07);
        currencies.put("SEK", 0.43);
        currencies.put("USD", 3.75);

        departureAirportOutbound = outbound.getDetails().getDepartureAirport().getName();
        departureAirportIataOutbound = outbound.getDetails().getDepartureAirport().getIataCode();
        arrivalCountryOutbound = outbound.getDetails().getArrivalAirport().getCountryName();
        arrivalAirportOutbound = outbound.getDetails().getArrivalAirport().getName();
        arrivalAirportIataOutbound = outbound.getDetails().getArrivalAirport().getIataCode();
        departureDateOutbound = outbound.getDetails().getDepartureDate();
        departureDateOutbound = departureDateOutbound.substring(0, departureDateOutbound.length() - 9);
        arrivalDateOutbound = outbound.getDetails().getArrivalDate();
        arrivalDateOutbound = arrivalDateOutbound.substring(0, arrivalDateOutbound.length() - 9);
        departureCountryOutbound = outbound.getDetails().getDepartureAirport().getCountryName();
        priceOutbound = outbound.getSummary().getPrice().getValue();
        currencyOutbound = outbound.getSummary().getPrice().getCurrencyCode();
        departureAirportInbound = inbound.getDetails().getDepartureAirport().getName();
        departureAirportIataInbound = inbound.getDetails().getDepartureAirport().getIataCode();
        arrivalCountryInbound = inbound.getDetails().getArrivalAirport().getCountryName();
        arrivalAirportInbound = inbound.getDetails().getArrivalAirport().getName();
        arrivalAirportIataInbound = inbound.getDetails().getArrivalAirport().getIataCode();
        departureDateInbound = inbound.getDetails().getDepartureDate();
        departureDateInbound = departureDateInbound.substring(0, departureDateInbound.length() - 9);
        arrivalDateInbound = inbound.getDetails().getArrivalDate();
        arrivalDateInbound = arrivalDateInbound.substring(0, arrivalDateInbound.length() - 9);
        departureCountryInbound = inbound.getDetails().getDepartureAirport().getCountryName();
        priceInbound = inbound.getSummary().getPrice().getValue();
        currencyInbound = inbound.getSummary().getPrice().getCurrencyCode();
        priceTotal = calculateTotal();
        isConnectionDirect();
        isDealGood();
    }

    private void isDealGood() {
        if (arrivalCountryOutbound.equals(departureCountryInbound) && priceTotal < MainActivity.maxPrice) {
            goodDeal = true;
        }
    }

    private void isConnectionDirect() {
        if (arrivalCountryOutbound.equals(departureCountryInbound)) {
            directConnection = true;
        }
    }

    private double calculateTotal() {
        double priceOutboundconverted = priceOutbound;
        double priceInboundconverted = priceInbound;

        for (Map.Entry<String, Double> entry : currencies.entrySet()) {
            if (entry.getKey().equals(currencyOutbound)) {
                priceOutboundconverted = entry.getValue() * priceOutbound;
            }
            if (entry.getKey().equals(currencyInbound)) {
                priceInboundconverted = entry.getValue() * priceInbound;
            }
        }

        return priceInboundconverted + priceOutboundconverted;
    }

    public boolean isGoodDeal() {
        return goodDeal;
    }

    public String getArrivalAirportOutbound() {
        return arrivalAirportOutbound;
    }

    public String getArrivalAirportIataOutbound() {
        return arrivalAirportIataOutbound;
    }

    public String getDepartureDateOutbound() {
        return departureDateOutbound;
    }

    public String getArrivalDateOutbound() {
        return arrivalDateOutbound;
    }

    public double getPriceOutbound() {
        return priceOutbound;
    }

    public String getCurrencyOutbound() {
        return currencyOutbound;
    }

    public String getDepartureCountryInbound() {
        return departureCountryInbound;
    }

    public String getDepartureAirportInbound() {
        return departureAirportInbound;
    }

    public String getDepartureAirportIataInbound() {
        return departureAirportIataInbound;
    }

    public String getArrivalCountryInbound() {
        return arrivalCountryInbound;
    }

    public String getArrivalAirportInbound() {
        return arrivalAirportInbound;
    }

    public String getArrivalAirportIataInbound() {
        return arrivalAirportIataInbound;
    }

    public String getDepartureDateInbound() {
        return departureDateInbound;
    }

    public String getArrivalDateInbound() {
        return arrivalDateInbound;
    }

    public double getPriceInbound() {
        return priceInbound;
    }

    public String getCurrencyInbound() {
        return currencyInbound;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public boolean isDirectConnection() {
        return directConnection;
    }

    public Flight getInbound() {
        return inbound;
    }

    public Flight getOutbound() {
        return outbound;
    }

    public String getDepartureCountryOutbound() {
        return departureCountryOutbound;
    }

    public String getDepartureAirportOutbound() {
        return departureAirportOutbound;
    }

    public String getDepartureAirportIataOutbound() {
        return departureAirportIataOutbound;
    }

    public String getArrivalCountryOutbound() {
        return arrivalCountryOutbound;
    }


    @Override
    public int compareTo(Opportunity o) {
        if (this.priceTotal < o.priceTotal)
            return -1;
        else if (o.priceTotal < this.priceTotal)
            return 1;
        return 0;
    }
}
