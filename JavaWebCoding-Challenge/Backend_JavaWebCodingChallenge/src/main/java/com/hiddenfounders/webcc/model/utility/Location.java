package com.hiddenfounders.webcc.model.utility;

import java.util.Arrays;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 1:06 AM
 * @package com.hiddenfounders.webcc.model
 */


public class Location {

    private String type;
    private double[] coordinates;

    
    public Location(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }


    public Location(double[] coordinates) {
        this.type = "Point";
        this.coordinates = coordinates;
    }


    public Location(){

    }

    public String getType() {
        return type;
    }

    public Location setType(String type) {
        this.type = type;
        return this;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public Location setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
        return this;
    }


    @Override
    public String toString() {
        return  "\"location\":{" +
                "\"type\": " + "\""+type+"\", "+
                "\"coordinates\": "+ Arrays.toString(coordinates)+
                "}";
    }
}
