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

public class OutOfRangeFromAdmin extends AppCompatActivity {
    DatabaseReference ref;
    ListView listView;
    OutFromAdmin user;
    ArrayList<OutFromAdmin> list;
    OutOfRangeCustomAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_of_range_from_admin);
        ref = FirebaseDatabase.getInstance().getReference();
        listView=(ListView)findViewById(R.id.ListViewOutOfRange);
        // user=new User();
        list=new ArrayList<>();
        adapter=new OutOfRangeCustomAdaptor(OutOfRangeFromAdmin.this,list);

    }
    @Override
    protected void onStart(){
        list.clear();
        ref.child("outofrange").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    user = ds.getValue(OutFromAdmin.class);
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
