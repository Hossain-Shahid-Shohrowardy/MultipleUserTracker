package com.example.multipleusertracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GpsOnCustomAdapter extends ArrayAdapter<On> {
    public Activity context;
    public ArrayList<On> list;
    public GpsOnCustomAdapter(Activity context,ArrayList<On> list){
        super(context,R.layout.gps_on_info,list);
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.gps_on_info,null,true);
        On u=list.get(position);
        TextView t1=view.findViewById(R.id.GpsOnroll);

        t1.setText("Roll- "+u.getRoll());


        return view;
    }
}
