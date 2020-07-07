package com.smile.spotapp.model;

public class Map_values {

    String lat,lang;

    public Map_values() {
    }

    public Map_values(String lat, String lang) {
        this.lat = lat;
        this.lang = lang;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
