package com.gmplaces.models;


public class Address {

    private long lat;
    private long lng;
    private String description;

    public  Address (long lat, long lng, String desc){
        this.lat = lat;
        this.lng = lng;
        this.description = desc;
    }

    public  Address (long lat, long lng){
        this.lat = lat;
        this.lng = lng;
        this.description =  "";
    }

    public long getLat() {
        return lat;
    }

    public long getLng() {
        return lng;
    }

    public String getDescription() {
        return description;
    }
}
