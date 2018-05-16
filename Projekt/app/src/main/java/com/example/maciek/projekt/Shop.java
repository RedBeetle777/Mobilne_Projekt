package com.example.maciek.projekt;

import java.util.List;

public class Shop {

    String name;
    ShopType type;

    public Shop(String name, ShopType type, double latitude, double longitude) {
       this.name = name;
       this.type = type;

       this.latitude = latitude;
       this.longitude = longitude;
    }

    double latitude;
    double longitude;

    public enum ShopType {KAWIARNIA, BAR, FASTFOOD, KINO, DWORZEC, CENTRUM_HANDLOWE};


}
