package com.gmplaces.models;


public class Address {

    private double lat;
    private double lng;
    private String description;

    public  Address (double lat, double lng, String desc){
        this.lat = lat;
        this.lng = lng;
        this.description = desc;
    }

    public  Address (double lat, double lng){
        this.lat = lat;
        this.lng = lng;
        this.description =  "";
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getDescription() {
        return description;
    }
}
