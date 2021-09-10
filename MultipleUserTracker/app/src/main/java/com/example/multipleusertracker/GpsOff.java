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

public class GpsOff extends AppCompatActivity {
    DatabaseReference ref;
    ListView listView;
    Off user;
    ArrayList<Off> list;
    GpsOffCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_off);
        ref = FirebaseDatabase.getInstance().getReference();
        listView=(ListView)findViewById(R.id.ListViewGpsOff);
        // user=new User();
        list=new ArrayList<>();
        adapter=new GpsOffCustomAdapter(GpsOff.this,list);
    }
    @Override
    protected void onStart(){
        list.clear();
        ref.child("admin").child("disable").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    user = ds.getValue(Off.class);
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
