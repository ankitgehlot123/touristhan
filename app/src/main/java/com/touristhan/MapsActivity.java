package com.touristhan;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import Utils.Utils;
import model.UserModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
        ,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private float zoomLevel = 12.0f;
    private DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef=mRootRef.child(Utils.userTable);
    private ArrayList<UserModel> userModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getUserData(){
        mUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    synchronized (this){
                        getAllData((Map<String,Object>) dataSnapshot.getValue());
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getAllData(Map<String,Object> users) {
        Log.wtf("ankit","all users "+users.size());

        //iterate through each user, ignoring their UID
        if(userModels==null)
            userModels = new ArrayList<>();


        for (Map.Entry<String, Object> entry : users.entrySet()){

            try{
                UserModel model = new UserModel();
                //Get user map
                Map singleUser = (Map) entry.getValue();
                //Get phone field and append to list
                String id = (String) singleUser.get("id");
                model.setId(id);

                String name = (String) singleUser.get("name");
                model.setName(name);

                String link = (String) singleUser.get("link");
                model.setLink(link);

                if(singleUser.get("latitude") instanceof Double){
                    String userLatitude = ((Double) singleUser.get("latitude")).toString();
                    model.setLatitude(userLatitude);
                } else if(singleUser.get("latitude") instanceof String){
                    String userLatitude = ((String) singleUser.get("latitude"));
                    model.setLatitude(userLatitude);
                }

                if(singleUser.get("longitude") instanceof Double){
                    String userLongitude = ((Double) singleUser.get("longitude")).toString();
                    model.setLongitude(userLongitude);
                } else if(singleUser.get("longitude") instanceof String){
                    String userLongitude = ((String) singleUser.get("longitude"));
                    model.setLongitude(userLongitude);
                }

                userModels.add(model);
                if(mMap!=null && userModels!=null && userModels.size()>0){
                    setInMap();
                }
            }catch (Exception ex){
                ex.printStackTrace();
                Log.wtf("ankit",ex.toString());
            }
        }
    }

    private void showToast(String message){
        Toast.makeText(MapsActivity.this,message,Toast.LENGTH_LONG).show();
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

        mMap.setOnInfoWindowClickListener(this);

        if(Utils.location!=null){
            LatLng myLocation = new LatLng(Utils.location.getLatitude(), Utils.location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Utils.location.getLatitude(), Utils.location.getLongitude()), zoomLevel));
        }

        getUserData();
    }

    private void setInMap(){
        mMap.clear();
        for (int i=0;i<userModels.size();i++){
            try{
                UserModel model = userModels.get(i);
                double latitude = Double.parseDouble(model.getLatitude());
                double longitude = Double.parseDouble(model.getLongitude());
                LatLng myLocation = new LatLng(latitude, longitude);


                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(myLocation)
                        .title(model.getName()));
                marker.setTag(model.getId());
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        String id = (String)marker.getTag();
        Intent facebookIntent = newFacebookIntent(getPackageManager(),"https://www.facebook.com/"+id);
        startActivity(facebookIntent);
    }

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
