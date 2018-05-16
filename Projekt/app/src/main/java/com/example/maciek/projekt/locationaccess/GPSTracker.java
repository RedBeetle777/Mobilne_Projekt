package com.example.maciek.projekt.locationaccess;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class GPSTracker extends Service implements LocationListener {

    private final Context ctx;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

    Location location;

    protected LocationManager locationManager;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60;

    public GPSTracker(Context ctx) {
        this.ctx = ctx;
        doGetLocation();
    }

    public Location getLocation() {
        return location;
    }

    public boolean canGetLocation() {
        return canGetLocation;
    }

    private Location doGetLocation() {
        try {
            locationManager = (LocationManager) ctx.getSystemService(LOCATION_SERVICE);

            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // pass
            } else {
                Location location = null;
                canGetLocation = true;
                if (isNetworkEnabled) {
                    location = getLocationFromNetwork();
                }
                if (isGPSEnabled) {
                    location = getLocationFromGPS();
                }

                if(location != null) {
                    this.location = location;
                }

                return location;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private Location getLocationFromNetwork() throws SecurityException {
       locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return location;
    }

    private Location getLocationFromGPS() throws SecurityException {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }

}
