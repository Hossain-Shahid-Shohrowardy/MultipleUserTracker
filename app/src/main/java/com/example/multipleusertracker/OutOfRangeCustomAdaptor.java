package com.example.multipleusertracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class OutOfRangeCustomAdaptor extends ArrayAdapter<OutFromAdmin> {
    public Activity context;
    public ArrayList<OutFromAdmin> list;
    public OutOfRangeCustomAdaptor(Activity context,ArrayList<OutFromAdmin> list){
        super(context,R.layout.out_of_range_info,list);
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.out_of_range_info,null,true);
        OutFromAdmin u=list.get(position);
        TextView t1=view.findViewById(R.id.Outroll);
        TextView t2=view.findViewById(R.id.Outdistance);
        TextView t3=view.findViewById(R.id.Outlatitude);
        TextView t4=view.findViewById(R.id.Outlongitude);
        t1.setText("Roll-"+u.getRoll());
        t2.setText("Distance-"+String.valueOf(u.getDistance()));
        t3.setText("Latitude-"+String.valueOf(u.getLatitude()));
        t4.setText("Longitude-"+String.valueOf(u.getLongitude()));


        return view;
    }

}
