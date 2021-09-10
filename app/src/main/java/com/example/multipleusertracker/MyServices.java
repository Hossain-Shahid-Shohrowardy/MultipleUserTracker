package com.example.multipleusertracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyServices extends Service {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public  static String key;
    public String dsroll;
  public   String   droll;

  HashMap<String,String>hashMap=new HashMap<String, String>();
  ArrayList<String>list=new ArrayList<String>();
  ArrayList<String> li=new ArrayList<String>();
    HashMap<String,ArrayList<String>>hash=new HashMap<String, ArrayList<String>>();
    HashMap<String,Float>has=new HashMap<String, Float>();


   public String eroll;
   int c;
   public  UpdateCluster updateCluster;
    public String deroll;
    public static boolean IsRunning = false;
    DatabaseReference ref;
    DatabaseReference r;
    public static Location location;
    TrackLocation trackLocation;
    LocationTrack locationtrack;
    Context context;
  // ArrayList<String> list;


    public MyServices() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ref = FirebaseDatabase.getInstance().getReference();
       // list=new ArrayList<String>();


    }
    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {

        //useremail = intent.getStringExtra("email");
        // Toast.makeText(MainActivity.getAppContext(),useremail,Toast.LENGTH_LONG).show();
        // Intent i = new Intent(this,TrackLocation.class);
        // i.putExtra("e", useremail);
        if(MainActivity.roll==1){
        locationtrack = new LocationTrack();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationtrack);


             }
        else
        {
            trackLocation = new TrackLocation();
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, trackLocation);
        }

        ref.child("students").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              // ref.child("inner").setValue(null);
                //ref.child("outers").setValue(null);
                int i = 2;
                int k;
                int s;
                int j = 0;
                int p = 0;
                int[] outer = new int[10000];

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Cluster c = ds.getValue(Cluster.class);
                    float distance = c.getDistance();
                    double latitude = c.getLatitude();
                    double longitude = c.getLongitude();
                    String roll = c.getRoll();

                    // Toast.makeText(MainActivity.mContext.getApplicationContext(),"out of range "+distance,Toast.LENGTH_LONG ).show();

                    if (distance > 100 && roll!=null ) {

                       // try {
                            ref.child("inner").child(roll).setValue(null);
                            ref.child("outers").child(roll).child("latitude").setValue(latitude);
                            ref.child("outers").child(roll).child("longitude").setValue(longitude);
                            ref.child("outers").child(roll).child("distance").setValue(distance);
                            ref.child("outers").child(roll).child("roll").setValue(roll);
                      //  } catch (NullPointerException ignored) {
                       // }

                    } else {
                        if(roll!=null )
                        { ref.child("outers").child(roll).setValue(null);
                          ref.child("clusters").child(roll).setValue(null);
                          ref.child("outofrange").child(roll).setValue(null);
                        ref.child("inner").child(roll).child("latitude").setValue(latitude);
                        ref.child("inner").child(roll).child("longitude").setValue(longitude);
                        ref.child("inner").child(roll).child("distance").setValue(distance);
                        ref.child("inner").child(roll).child("roll").setValue(roll);

                        }
                    }


                }


            }
        

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* ref.child("inner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Inner inner = ds.getValue(Inner.class);
                    float distance = inner.getDistance();
                    double latitude = inner.getLatitude();
                    double longitude = inner.getLongitude();
                    String roll = inner.getRoll();
                    if (distance > 100 && roll!=null ){

                        ref.child("inner").child(roll).setValue(null);
                        ref.child("outers").child(roll).child("latitude").setValue(latitude);
                        ref.child("outers").child(roll).child("longitude").setValue(longitude);
                        ref.child("outers").child(roll).child("distance").setValue(distance);
                        ref.child("outers").child(roll).child("roll").setValue(roll);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       ref.child("outers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot de:dataSnapshot.getChildren()){

                    Update update=de.getValue(Update.class);
                    String roll=update.getRoll();
                    float distance=update.getDistance();
                    double latitude=update.getLatitude();
                    double longitude=update.getLongitude();


                    if(distance < 100 && roll!=null ){

                            ref.child("outers").child(roll).setValue(null);
                        ref.child("inner").child(roll).child("latitude").setValue(latitude);
                        ref.child("inner").child(roll).child("longitude").setValue(longitude);
                        ref.child("inner").child(roll).child("distance").setValue(distance);
                        ref.child("inner").child(roll).child("roll").setValue(roll);
                        }



                    }
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
      // if(ref.child("outers").equals(null)) {
           ref.child("outers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(hashMap!=null){
                hashMap.clear();}
                if(hash!=null){
                    hash.clear();}
               // ref.child("clusters").setValue(null);
               // ref.child("outofrange").setValue(null);
                 c=0;
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    if(li!=null){
                        list.removeAll(li);}
                  //  if(has!=null){has.clear();}
                    Outer outer   =ds.getValue(Outer.class);
                   dsroll=outer.getRoll();
                   double stdlat=outer.getLatitude();
                   double stdlong=outer.getLongitude();
                    float outerdis=outer.getDistance();
                    if(dsroll!=null){
                    hashMap.put(dsroll,dsroll);}
                    for(DataSnapshot d:dataSnapshot.getChildren()){
                        if(has!=null){has.clear();}
                        Divider divider=d.getValue(Divider.class);
                       droll=divider.getRoll();
                       double endlat=divider.getLatitude();
                       double endlong=divider.getLongitude();
                        float results[]=new float[2];
                     Location.distanceBetween(stdlat,stdlong,endlat,endlong,results);
                     float dis=results[0];
                        has.put(droll,dis);
                     if(dis <= 15 && dsroll!=null && droll!=null){
                        // if(li!=null){
                        // list.removeAll(li);}
                         list.add(droll);
                        // Distance du=new Distance(dis);
                        // float innerdis=du.getDistance();
                         //has.put(droll,dis);
                        // try{
                         ref.child("clusters").child(dsroll).child(droll).child("stdlatitude").setValue(stdlat);
                         ref.child("clusters").child(dsroll).child(droll).child("stdlongitude").setValue(stdlong);
                         ref.child("clusters").child(dsroll).child(droll).child("endlatitude").setValue(endlat);
                         ref.child("clusters").child(dsroll).child(droll).child("endlongitude").setValue(endlong);
                         ref.child("clusters").child(dsroll).child(droll).child("distance").setValue(dis);
                         ref.child("clusters").child(dsroll).child(droll).child("roll").setValue(droll);


                         c++;

                     }
                        if(dsroll!=null && droll!=null ){

                            if(has.get(droll)>15 ){
                                ref.child("clusters").child(dsroll).child(droll).setValue(null);
                            }
                        }
                    }
                    hash.put(dsroll,list);
                    li=list;

                if(c==1 && dsroll!=null){
                       // try{

                         ref.child("outofrange").child(dsroll).child("latitude").setValue(stdlat);
                        ref.child("outofrange").child(dsroll).child("longitude").setValue(stdlong);
                        ref.child("outofrange").child(dsroll).child("distance").setValue(outerdis);
                        ref.child("outofrange").child(dsroll).child("roll").setValue(dsroll);

                    }
                    c=0;


                            if(list.size()>1){
                                ref.child("outofrange").child(dsroll).setValue(null);
                            }




                }
               /* for(Map.Entry<String, ArrayList<String>> ha:hash.entrySet() ){
                    String key=ha.getKey();
                    ArrayList<String> list=ha.getValue();
                    if(key!=null){
                    if(list.size()>1){
                        ref.child("outofrange").child(key).setValue(null);
                    }
                    }
                }*/
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });






      /*  ref.child("clusters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ref.child("outers").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(  DataSnapshot de:dataSnapshot.getChildren()){
                              updateCluster=de.getValue(UpdateCluster.class);
                            deroll=updateCluster.getRoll();
                            double distance=updateCluster.getDistance();
                            double latitude=updateCluster.getLatitude();
                            double longitude=updateCluster.getLongitude();
                            if( deroll!=null){}
                            else{
                            if(distance < 100 ){
                                ref.child("clusters").child(deroll).setValue(null);
                            }
                            else{
                            ref.child("clusters").child(deroll).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                                        UpdateCluster2 updateCluster2 = d.getValue(UpdateCluster2.class);
                                        double stdlat=updateCluster2.getStdlatitude();
                                        double stdlong=updateCluster2.getStdlongitude();
                                        double endlat=updateCluster2.getEndlatitude();
                                        double endlong=updateCluster2.getEndlongitude();
                                        float di = updateCluster2.getDistance();
                                        String droll = updateCluster2.getRoll();
                                        if (di > 15 && deroll!=null && droll!=null) {
                                            ref.child("clusters").child(deroll).child(droll).setValue(null);
                                           // String r= dataSnapshot.child("clusters").child(deroll).child(droll).getValue(String.class);
                                           // if(r==null){
                                               // ref.child("clusters").child(deroll).child(droll).child("stdlatitude").setValue(stdlat);
                                               // ref.child("clusters").child(deroll).child(droll).child("stdlongitude").setValue(stdlong);
                                               // ref.child("clusters").child(deroll).child(droll).child("endlatitude").setValue(endlat);
                                              //  ref.child("clusters").child(deroll).child(droll).child("endlongitude").setValue(endlong);
                                              //  ref.child("clusters").child(deroll).child(droll).child("distance").setValue(di);
                                              //  ref.child("clusters").child(deroll).child(droll).child("roll").setValue(droll);

                                           // }

                                        }

                                    }

                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            }}
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                    }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.child("outofrange").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Out out=ds.getValue(Out.class);
                     eroll=out.getRoll();
                    float distance=out.getDistance();

                    if(distance < 100 && eroll!=null){
                        ref.child("outofrange").child(eroll).setValue(null);
                    }
                    else{
                       // try {
                           if(eroll!=null){

                        ref.child("clusters").child(eroll).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int t = 0;
                                for (DataSnapshot dp : dataSnapshot.getChildren()) {
                                    t++;
                                }
                                if (t > 1 ) {
                                    ref.child("outofrange").child(eroll).setValue(null);
                                }
                                t = 0;
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });}
                    //}catch (NullPointerException ignored){}
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/





        return START_NOT_STICKY;
    }

}
