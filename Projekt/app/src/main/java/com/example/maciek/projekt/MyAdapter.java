package com.example.maciek.projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter<T> extends BaseAdapter implements ListAdapter {

    ArrayList<Shop> shopList;
    Context context;

    public MyAdapter(Context context, ArrayList shopList) {
        this.shopList = shopList;
        this.context = context;
    }

    public void add(Shop shop) {
        shopList.add(shop);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list, null);
        }

        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        Shop temp = shopList.get(position);
        listItemText.setText(temp.name + " (" + temp.type + "), " + temp.longitude + ", " + temp.latitude);

        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopList.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

