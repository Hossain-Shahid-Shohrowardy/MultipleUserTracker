package com.example.multipleusertracker;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<User> {
    public Activity context;
    public ArrayList<User>list;
    public CustomAdapter(Activity context,ArrayList<User> list){
        super(context,R.layout.user_info,list);
        this.list=list;
        this.context=context;


    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.user_info,null,true);
        User u=list.get(position);
        TextView t1=view.findViewById(R.id.stdroll);
        TextView t2=view.findViewById(R.id.stddistance);
        TextView t3=view.findViewById(R.id.stdlatitude);
        TextView t4=view.findViewById(R.id.stdlongitude);
        t1.setText("Roll-"+u.getRoll());
        t2.setText("Distance-"+String.valueOf(u.getDistance()));
        t3.setText("Latitude-"+String.valueOf(u.getLatitude()));
        t4.setText("Longitude-"+String.valueOf(u.getLongitude()));


        return view;
    }
}
