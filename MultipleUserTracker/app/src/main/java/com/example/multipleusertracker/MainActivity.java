package com.example.multipleusertracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    TrackLocation trackLocation;
    PendingIntent pendingIntent1;
    PendingIntent pendingIntent2;
    PendingIntent pendingIntent3;
    public static final String chanelId="location_tracking";
    public static final String chanelName="location";
    public static final String chanelDescription="to_location_track";
    public static Context mContext;
    public static int roll;
    public int i;
    public int j;
 public   ArrayList<String> e=new ArrayList<String>();
    public   ArrayList<String> s=new ArrayList<String>();
    public   ArrayList<String> out=new ArrayList<String>();
 public ArrayList<String> disu=new ArrayList<String>();
    public ArrayList<String> enu=new ArrayList<String>();
    public ArrayList<String> outer=new ArrayList<String>();


    public String password;
    DatabaseReference ref;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  mContext = this.getApplicationContext();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel chanel= new NotificationChannel(chanelId,chanelName, NotificationManager.IMPORTANCE_DEFAULT);
            chanel.setDescription(chanelDescription);
            NotificationManager notificationManager=  getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(chanel);
           // Intent in1=new Intent(this,GpsOff.class);
            // pendingIntent1=PendingIntent.getActivity(this,5,in1,PendingIntent.FLAG_UPDATE_CURRENT);
        }
        Intent in1=new Intent(this,GpsOff.class);
        pendingIntent1=PendingIntent.getActivity(this,5,in1,PendingIntent.FLAG_UPDATE_CURRENT);
        Intent in2=new Intent(this,GpsOn.class);
        pendingIntent2=PendingIntent.getActivity(this,6,in2,PendingIntent.FLAG_UPDATE_CURRENT);
        Intent in3=new Intent(this,OutOfRangeFromAdmin.class);
        pendingIntent3=PendingIntent.getActivity(this,7,in3,PendingIntent.FLAG_UPDATE_CURRENT);
        editText1 = (EditText) findViewById(R.id.roll);
        editText2 = (EditText) findViewById(R.id.password);
        mContext = this.getApplicationContext();
        ref = FirebaseDatabase.getInstance().getReference();


    }
    public static Context getAppContext(){
        return mContext;
    }

    public void buLogin(View v) {
        i=0;
        j=0;
        String  r= editText1.getText().toString();
        password = editText2.getText().toString();
         roll = Integer.valueOf(r);

        CheckUserPermsions();
        try{
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                if(i==0){i++;}
                else{
                    ref.child("admin").child("disable").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            disu.removeAll(e);
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                Disable disable=ds.getValue(Disable.class);
                                String r=disable.getRoll();
                               disu.add(r);

                            }
                            //disu.add("are disable their gps");
                            e=disu;

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(disu.size()==0){}
                    else{

                    NotificationCompat.Builder mbuilder=
                        new NotificationCompat.Builder(MainActivity.mContext.getApplicationContext(),MainActivity.chanelId)
                                .setSmallIcon(R.drawable.gpsoff)
                                .setContentTitle("Gps off")
                                .setContentText("Roll- "+disu+"are disable their gps")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent1)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManager notificationManager=(NotificationManager)MainActivity.mContext.getApplicationContext().
                        getSystemService(MainActivity.mContext.getApplicationContext().NOTIFICATION_SERVICE);
                notificationManager.notify(1,mbuilder.build());


               // disu.removeAll(e);
                    }
                    ref.child("admin").child("enable").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            enu.removeAll(s);
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                Enable enable=ds.getValue(Enable.class);
                                String r=enable.getRoll();
                                enu.add(r);

                            }
                            //disu.add("are disable their gps");
                            s=enu;

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(enu.size()==0){}
                    else{
                        NotificationCompat.Builder mbuilder1=
                                new NotificationCompat.Builder(MainActivity.mContext.getApplicationContext(),MainActivity.chanelId)
                                        .setSmallIcon(R.drawable.gpsoff)
                                        .setContentTitle("Gps on")
                                        .setContentText("Roll- "+enu+"are enable their gps")
                                        .setAutoCancel(true)
                                        .setContentIntent(pendingIntent2)
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        NotificationManager notificationManager=(NotificationManager)MainActivity.mContext.getApplicationContext().
                                getSystemService(MainActivity.mContext.getApplicationContext().NOTIFICATION_SERVICE);
                        notificationManager.notify(2,mbuilder1.build());


                        // disu.removeAll(e);
                    }
                    ref.child("outofrange").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            outer.removeAll(out);
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                               FindOuter findOuter=ds.getValue(FindOuter.class);
                               String r=findOuter.getRoll();
                               String distance=String.valueOf(findOuter.getDistance());
                               if(r==null || distance==null){}
                               else{
                               outer.add(r+"-"+distance);
                               }

                            }
                            //disu.add("are disable their gps");
                            out=outer;

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    if(outer.size()==0){}
                    else{
                        NotificationCompat.Builder mbuilder2=
                                new NotificationCompat.Builder(MainActivity.mContext.getApplicationContext(),MainActivity.chanelId)
                                        .setSmallIcon(R.drawable.gpsoff)
                                        .setContentTitle("Out Of Range")
                                        .setContentText("Roll-Distance "+outer+"out of range")
                                        .setAutoCancel(true)
                                        .setContentIntent(pendingIntent3)
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        NotificationManager notificationManager=(NotificationManager)MainActivity.mContext.getApplicationContext().
                                getSystemService(MainActivity.mContext.getApplicationContext().NOTIFICATION_SERVICE);
                        notificationManager.notify(3,mbuilder2.build());


                        // disu.removeAll(e);
                    }

                }
                //

            }
        };

// schedule the task to run starting now and then every hour...
        timer.schedule (hourlyTask, 0l, 1000*20);}catch (NullPointerException ignored){}
       //  CheckUserPermsions();



    }

    public void CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }


        StartServices();

    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    StartServices();
                } else {
                    // Permission D

                    Toast.makeText(this, "permission has not been granted", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void StartServices() {


        Intent intent=new Intent(this,MyServices.class);
        startService(intent);

    }
   public void buviewLogin(View v){
       Intent intent2 =new Intent(this,Retrieve.class);
       startActivity(intent2);
   }
   public void viewMap(View view){
       Intent intent3 =new Intent(this,MapsActivity.class);
       startActivity(intent3);

   }


}
