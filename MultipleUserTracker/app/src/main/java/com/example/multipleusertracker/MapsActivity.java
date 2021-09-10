package com.example.multipleusertracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    DatabaseReference ref;
    Marker inner;
    Marker clusterhead;
    Marker memberofcluster;
    String hroll;
    Marker marker;
    Circle circle;
    Circle headcircle;
    Circle outercircle;
    Marker omarker;
    public ArrayList<Marker>admin=new ArrayList<Marker>();
    public ArrayList<Marker>cadmin=new ArrayList<Marker>();
    public HashMap<String,Marker> outer=new HashMap<String,Marker>();
    public HashMap<String,Marker> head=new HashMap<String,Marker>();
    public HashMap<String,Marker> member=new HashMap<String,Marker>();
    public HashMap<String,Circle> chead=new HashMap<String,Circle>();
    public ArrayList<Marker>couter=new ArrayList<Marker>();

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ref = FirebaseDatabase.getInstance().getReference();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        ref = FirebaseDatabase.getInstance().getReference();

        ref.child("admin").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {


                    //  admin.removeAll(cadmin);
                    double latitude = dataSnapshot.child("latitude").getValue(double.class);
                    double longitude = dataSnapshot.child("longitude").getValue(double.class);
                    //Log.d("location", "lat="+latitude+" long"+longitude);
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (marker != null) {
                        marker.remove();
                        if (circle != null) {
                            circle.remove();
                        }
                        if (outercircle != null) {
                            outercircle.remove();
                        }
                    }
                    // mMap.addMarker(new MarkerOptions().position(latLng).title("Admin"));
                    // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Admin").snippet("Latitude:-" + latitude + " Longitude:-" + longitude).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                    marker = mMap.addMarker(markerOptions);
                    circle = mMap.addCircle(new CircleOptions().center(latLng).radius(99).strokeColor(Color.RED));
                    outercircle = mMap.addCircle(new CircleOptions().center(latLng).radius(150).strokeColor(Color.RED));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));



                    // MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Marker");
                    // Marker marker = mMap.addMarker(markerOptions);
                } catch (NullPointerException ignored) {
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.child("inner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        OuterMap outerMap = ds.getValue(OuterMap.class);
                        String roll = outerMap.getRoll();
                        double latitude = outerMap.getLatitude();
                        double longitude = outerMap.getLongitude();
                        float distance = outerMap.getDistance();
                        LatLng latLng = new LatLng(latitude, longitude);
                        if (outer.get(roll) != null) {
                            Marker m = outer.get(roll);
                            m.remove();

                        }
                        if(distance<=100 &&roll!=null){
                        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Student").snippet("Roll:-" + roll + " Distance:-" + distance).
                                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                        inner = mMap.addMarker(markerOptions);

                        outer.put(roll, inner);
                        if(distance>99 && distance<100 ){
                            Marker mar=outer.get(roll);
                            mar.remove();

                        }
                        }


                    } catch (NullPointerException ignored) {
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
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    final MapClusterHead mapClusterHead = ds.getValue(MapClusterHead.class);
                    hroll = mapClusterHead.getRoll();
                    double latitude = mapClusterHead.getLatitude();
                    double longitude = mapClusterHead.getLongitude();
                    float distance = mapClusterHead.getDistance();
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (head.get(hroll) != null) {
                        Marker m = head.get(hroll);
                        m.remove();
                        if (chead.get(hroll) != null) {
                            Circle c = chead.get(hroll);
                            c.remove();
                        }
                    }if(distance>100 && hroll!=null){
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Student").snippet("Roll:-" + hroll + " Distance From Admin:-" + distance).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    clusterhead = mMap.addMarker(markerOptions);
                    headcircle = mMap.addCircle(new CircleOptions().center(latLng).radius(15).strokeColor(Color.GREEN));
                    head.put(hroll, clusterhead);
                    chead.put(hroll, headcircle);
                    if(distance>100 && distance<101){
                        Marker ma=head.get(hroll);
                        Circle cir=chead.get(hroll);
                        ma.remove();
                        cir.remove();
                    }
                    }
                  /*  ref.child("clusters").child(hroll).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot d:dataSnapshot.getChildren()){
                                MemberOfCluster memberOfCluster=d.getValue(MemberOfCluster.class);
                                String r=memberOfCluster.getRoll();
                                double lat=memberOfCluster.getLatitude();
                                double longi=memberOfCluster.getLatitude();
                                float dis=memberOfCluster.getDistance();
                                LatLng latL = new LatLng(lat, longi);
                                if(member.get(r)!=null){
                                    Marker me=member.get(r);
                                    me.remove();
                                }
                                MarkerOptions markerO = new MarkerOptions().position(latL).title("Cluster Member").snippet("Roll:-"+r+" Distance from "+hroll+":-"+dis).
                                        icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                                memberofcluster = mMap.addMarker(markerO);
                                member.put(r,memberofcluster);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
