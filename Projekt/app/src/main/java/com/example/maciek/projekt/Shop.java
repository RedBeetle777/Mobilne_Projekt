package com.example.maciek.projekt;

public class Shop {

    long id;
    String name;
    ShopType type;
    double latitude;
    double longitude;

    public Shop(long id, String name, ShopType type, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ShopType getType() {
        return type;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public enum ShopType {KAWIARNIA, BAR, FASTFOOD, KINO, DWORZEC, CENTRUM_HANDLOWE;}

    ;

}
