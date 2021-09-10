package com.example.multipleusertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GpsOn extends AppCompatActivity {
    DatabaseReference ref;
    ListView listView;
    On user;
    ArrayList<On> list;
    GpsOnCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_on);
        ref = FirebaseDatabase.getInstance().getReference();
        listView=(ListView)findViewById(R.id.ListViewGpsOn);
        // user=new User();
        list=new ArrayList<>();
        adapter=new GpsOnCustomAdapter(GpsOn.this,list);
    }
    @Override
    protected void onStart(){
        list.clear();
        ref.child("admin").child("enable").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    user = ds.getValue(On.class);
                    list.add(user);



                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
