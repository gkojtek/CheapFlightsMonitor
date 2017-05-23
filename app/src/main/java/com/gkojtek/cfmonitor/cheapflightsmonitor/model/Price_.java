
package com.gkojtek.cfmonitor.cheapflightsmonitor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price_ {

    @SerializedName("value")
    @Expose
    private double value;
    @SerializedName("valueMainUnit")
    @Expose
    private String valueMainUnit;
    @SerializedName("valueFractionalUnit")
    @Expose
    private String valueFractionalUnit;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("currencySymbol")
    @Expose
    private String currencySymbol;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getValueMainUnit() {
        return valueMainUnit;
    }

    public void setValueMainUnit(String valueMainUnit) {
        this.valueMainUnit = valueMainUnit;
    }

    public String getValueFractionalUnit() {
        return valueFractionalUnit;
    }

    public void setValueFractionalUnit(String valueFractionalUnit) {
        this.valueFractionalUnit = valueFractionalUnit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

}
