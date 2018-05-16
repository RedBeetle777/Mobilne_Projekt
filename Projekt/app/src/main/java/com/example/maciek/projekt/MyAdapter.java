package com.example.maciek.projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter<T> extends BaseAdapter implements ListAdapter {

    ArrayList todo;
    Context context;

    public MyAdapter(Context context, ArrayList<T> todo) {
        this.todo = todo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todo.size();
    }

    @Override
    public Object getItem(int position) {
        return todo.get(position);
    }

    //tutaj nie wiem co trzeba zwracać
    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(T obj) {
        todo.add(obj);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_list, null);
        }

        TextView listItemText = view.findViewById(R.id.list_item_string);
        listItemText.setText(((T) getItem(position)).toString());


        Button deleteBtn = view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                todo.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

