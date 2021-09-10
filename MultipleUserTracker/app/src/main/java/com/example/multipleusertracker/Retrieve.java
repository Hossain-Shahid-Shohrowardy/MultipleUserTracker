package com.example.multipleusertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Retrieve extends AppCompatActivity {
FirebaseDatabase database;
public static Context rContext;
DatabaseReference ref;
ListView listView;
User user;
ArrayList<User> list;
CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        rContext = this.getApplicationContext();
        ref = FirebaseDatabase.getInstance().getReference();
        listView=(ListView)findViewById(R.id.ListView);
         // user=new User();
        list=new ArrayList<>();
        adapter=new CustomAdapter(Retrieve.this,list);

    }
    public static Context rgetAppContext(){
        return rContext;
    }
    @Override
    protected void onStart(){
        list.clear();
        ref.child("students").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    user = ds.getValue(User.class);
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
