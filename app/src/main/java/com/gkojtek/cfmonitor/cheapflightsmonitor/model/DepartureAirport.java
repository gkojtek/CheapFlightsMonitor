
package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureAirport {

    @SerializedName("iataCode")
    @Expose
    private String iataCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("seoName")
    @Expose
    private String seoName;
    @SerializedName("countryName")
    @Expose
    private String countryName;

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeoName() {
        return seoName;
    }

    public void setSeoName(String seoName) {
        this.seoName = seoName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
