package com.example.multipleusertracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.LDAPCertStoreParameters;

public class TrackLocation implements LocationListener {
public  double longitude;
public double lat;
public double longi;
public String num;
Context context;
public  double latitude;
DatabaseReference ref;
DatabaseReference reference;

    @Override
    public void onLocationChanged(Location location) {
        ref = FirebaseDatabase.getInstance().getReference();
        this.latitude=location.getLatitude();
        this.longitude=location.getLongitude();
      // this.num="1";
        //if(MainActivity.roll==this.num){
       // ref.child("admin").child(this.num).
              //  child("latitude").setValue(this.latitude);
       // ref.child("admin").child(this.num).
              //  child("longitude").setValue(this.longitude);
      //  }
      //  else {
       // if(this.lat!=0 && this.longi!=0 && this.latitude!=0 && this.longitude!=0){
            ref.child("students").child(String.valueOf(MainActivity.roll)).
                    child("latitude").setValue(this.latitude);
            ref.child("students").child(String.valueOf(MainActivity.roll)).
                   child("longitude").setValue(this.longitude);
        ref.child("students").child(String.valueOf(MainActivity.roll)).
                child("roll").setValue(String.valueOf(MainActivity.roll));
            ref.child("admin").child(String.valueOf(1)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  String  stringlat = dataSnapshot.child("latitude").getValue().toString();
                   String  stringlog = dataSnapshot.child("longitude").getValue().toString();
                    lat=Double.valueOf(stringlat);
                    longi=Double.valueOf(stringlog);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

               }
            });
           float results[]=new float[1];
           if(this.lat!=0 && this.longi!=0 && this.latitude!=0 && this.longitude!=0){
            Location.distanceBetween(this.lat,this.longi,this.latitude,this.longitude,results);
           float distance=results[0];
            ref.child("students").child(String.valueOf(MainActivity.roll)).child("distance").setValue(distance); }


       // }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        ref = FirebaseDatabase.getInstance().getReference();
        try{
        ref.child("admin").child("enable").child(String.valueOf(MainActivity.roll)).
                child("roll").setValue(String.valueOf(MainActivity.roll));}catch (NullPointerException ignored){}
        Toast.makeText(MainActivity.mContext.getApplicationContext(),
                "  GPS turned on from "+MainActivity.roll,
                Toast.LENGTH_LONG).show();


    }

    @Override
    public void onProviderDisabled(String s) {
        ref = FirebaseDatabase.getInstance().getReference();
        try{
        ref.child("admin").child("disable").child(String.valueOf(MainActivity.roll)).
                child("roll").setValue(String.valueOf(MainActivity.roll));}
        catch (NullPointerException ignored){}
        Toast.makeText(MainActivity.mContext.getApplicationContext(),
                "GPS turned on off from "+MainActivity.roll,
                Toast.LENGTH_LONG).show();

    }
}
