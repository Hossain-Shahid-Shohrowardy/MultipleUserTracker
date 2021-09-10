package com.example.multipleusertracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GpsOffCustomAdapter extends ArrayAdapter<Off> {
    public Activity context;
    public ArrayList<Off> list;
    public GpsOffCustomAdapter(Activity context,ArrayList<Off> list){
        super(context,R.layout.gps_off_info,list);
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.gps_off_info,null,true);
        Off u=list.get(position);
        TextView t1=view.findViewById(R.id.GpsOffroll);

        t1.setText("Roll- "+u.getRoll());


        return view;
    }


    }
