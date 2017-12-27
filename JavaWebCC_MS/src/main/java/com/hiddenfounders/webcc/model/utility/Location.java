package com.hiddenfounders.webcc.model.utility;

/**
 * @author Mohamed SALHI
 * @date Created on 12/26/17 - 1:06 AM
 * @package com.hiddenfounders.webcc.model
 */


public class Location {

    private String type;
    private Integer[] cordinates;


    public Location(String type, Integer[] cordinates) {
        this.type = type;
        this.cordinates = cordinates;
    }


    public Location(Integer[] cordinates) {
        this.type = "Point";
        this.cordinates = cordinates;
    }


    public String getType() {
        return type;
    }

    public Location setType(String type) {
        this.type = type;
        return this;
    }

    public Integer[] getGeo() {
        return cordinates;
    }

    public Location setGeo(Integer[] cordinates) {
        this.cordinates = cordinates;
        return this;
    }
}
