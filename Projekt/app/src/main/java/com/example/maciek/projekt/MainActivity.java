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

public class MainActivity extends AppCompatActivity {

    private ListView list ;
    private ArrayAdapter<String> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list_view);
        final EditText et = findViewById(R.id.base);

        String cars[] = {"Mercedes", "Fiat", "Ferrari", "Aston Martin", "Lamborghini", "Skoda", "Volkswagen", "Audi", "Citroen"};

        ArrayList<String> carL = new ArrayList<String>();
        carL.addAll( Arrays.asList(cars) );

        adapter = new ArrayAdapter<String>(this, R.layout.row, carL);
        list.setAdapter(adapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                adapter.add(text);

            }
        };

        add.setOnClickListener(listener);



    }


}
