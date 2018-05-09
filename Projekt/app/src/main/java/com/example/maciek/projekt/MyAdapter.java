package com.example.maciek.projekt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 09.05.2018.
 */

public class MyAdapter extends BaseAdapter implements ListAdapter{

    ArrayList todo;
    Context context;
    public MyAdapter(Context context, ArrayList todo){
        this.todo = todo;
        this.context = context;
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
