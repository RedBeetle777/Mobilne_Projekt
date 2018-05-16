package com.example.maciek.projekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private ListView list ;
    private MyAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = findViewById(R.id.base);

        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

        ArrayList<String> carL = new ArrayList(Arrays.asList(cars));

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.list_view);
        adapter = new MyAdapter(this, carL);
//
        lView.setAdapter(adapter);

        Button add = findViewById(R.id.add);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString();
                adapter.add(text);
            }
        };

        add.setOnClickListener(listener);
    }

}
