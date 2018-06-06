package com.example.maciek.projekt;

import android.app.Activity;
import android.location.Location;

import com.example.maciek.projekt.dbaccess.ShopDbAdapter;
import com.example.maciek.projekt.locationaccess.GPSTracker;

public class Example extends Activity {

    public Example() {
        ShopDbAdapter db = new ShopDbAdapter(getApplicationContext());
        db.open();
        GPSTracker gps = new GPSTracker(getApplicationContext());

        // Counter, zeby nadawac nowe id sklepom
        long shopIdCounter = 0;

        // Dodanie nowego sklepu
        if (gps.canGetLocation()) {
            Location loc = gps.getLocation();
            db.insertShop(new Shop(shopIdCounter++, "McDonalds", Shop.ShopType.FASTFOOD, loc.getLatitude(), loc.getLongitude()));
        } else {
            gps.stopUsingGPS();
            throw new RuntimeException("Couldn't get GPS location");
        }

        // Zamkniecie zasobow
        gps.stopUsingGPS();
        db.close();
    }

}
