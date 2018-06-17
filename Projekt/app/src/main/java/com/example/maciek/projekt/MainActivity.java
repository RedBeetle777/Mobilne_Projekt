package com.example.maciek.projekt;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.maciek.projekt.dbaccess.ShopDbAdapter;
import com.example.maciek.projekt.locationaccess.GPSTracker;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.maciek.projekt.Shop.ShopType.KINO;
import static com.example.maciek.projekt.Shop.ShopType.KAWIARNIA;
import static com.example.maciek.projekt.Shop.ShopType.BAR;
import static com.example.maciek.projekt.Shop.ShopType.FASTFOOD;
import static com.example.maciek.projekt.Shop.ShopType.DWORZEC;
import static com.example.maciek.projekt.Shop.ShopType.CENTRUM_HANDLOWE;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private ListView list ;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = findViewById(R.id.base);

        //enum shop{CINEMA_CITY};

        Shop shop1 = new Shop(1,"CinemaCity", KINO, 90, 90);
        Shop shop2 = new Shop(2, "Pasibus", FASTFOOD, 90, 90);
        //String[] carL = {"Cinema City", "KFC", "Pasibus", "Cybermachina", "Wroclavia"};

        ArrayList<Shop> shopList = new ArrayList<Shop>();
        shopList.add(shop1);
        shopList.add(shop2);

        adapter = new MyAdapter (this, shopList);


        //generate list
        //ArrayList<String> list = new ArrayList<String>();
        //list.add("item1");
        //list.add("item2");

        //instantiate custom adapter
        //MyAdapter adapter = new MyAdapter(, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.list_view);
        adapter = new MyAdapter(this, shopList);

        String[] elementy = {}; // enum zamienić na tablicę Stringów
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, elementy);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lView.setAdapter(adapter);

        Button add = findViewById(R.id.add);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = 100;
                String text = et.getText().toString();
                String type = spinner.getSelectedItem().toString();
                GPSTracker tracker = new GPSTracker(getApplicationContext());
                if(tracker.canGetLocation()) {
                    Location location = tracker.getLocation();

                    Shop shop = new Shop(id,text, Shop.ShopType.valueOf(type), location.getLatitude(), location.getLongitude());

                    adapter.add(shop);
                } else{
                    Toast.makeText(getApplicationContext(), "Couldn't get location", Toast.LENGTH_SHORT).show();
                }
                tracker.stopUsingGPS();
            }
        };


        add.setOnClickListener(listener);
    }
    //AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "locations").allowMainThreadQueries().build();

    //ShopDbAdapter db = Room.ShopDbAdapter(getApplicationContext());
    ShopDbAdapter db = Room.databaseBuilder(getApplicationContext(), ShopDbAdapter.class, "locations").allowMainThreadQueries().build();
        //db.open();
    GPSTracker gps = new GPSTracker(getApplicationContext());

    // Counter, zeby nadawac nowe id sklepom


    long shopIdCounter = 2;


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
