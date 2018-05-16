package com.example.maciek.projekt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.maciek.projekt.Shop.ShopType.KINO;

public class MainActivity extends AppCompatActivity {

    private ListView list ;
    private MyAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list_view);
        final EditText et = findViewById(R.id.base);

        //enum shop{CINEMA_CITY};

        Shop shop = new Shop("CinemaCity", KINO, 90, 90);
        //String[] carL = {"Cinema City", "KFC", "Pasibus", "Cybermachina", "Wroclavia"};

        ArrayList<Shop> shopList = new ArrayList<Shop>();
        shopList.add(shop);

        adapter = new MyAdapter (this, shopList);


        //generate list
        //ArrayList<String> list = new ArrayList<String>();
        //list.add("item1");
        //list.add("item2");

        //instantiate custom adapter
        //MyAdapter adapter = new MyAdapter(, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.list_view);
        lView.setAdapter(adapter);

        Button add = findViewById(R.id.add);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString();
                Shop shop = new Shop(text, KINO,90, 90);
                adapter.add(shop);

            }
        };

        add.setOnClickListener(listener);



    }


}
