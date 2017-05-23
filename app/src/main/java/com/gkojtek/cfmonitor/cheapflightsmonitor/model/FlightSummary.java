
package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightSummary {

    @SerializedName("price")
    @Expose
    private Price_ price;
    @SerializedName("newRoute")
    @Expose
    private boolean newRoute;

    public Price_ getPrice() {
        return price;
    }

    public void setPrice(Price_ price) {
        this.price = price;
    }

    public boolean isNewRoute() {
        return newRoute;
    }

    public void setNewRoute(boolean newRoute) {
        this.newRoute = newRoute;
    }

}
