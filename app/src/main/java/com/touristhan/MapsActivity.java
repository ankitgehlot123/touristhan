package com.touristhan;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

                String pic = (String) singleUser.get("picture");
                model.setPicture(pic);

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
                Log.wtf("ankit",model.getPicture().toString());

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(myLocation)
                        .title(model.getName())
                         );
                loadMarkerIcon(marker,model.getPicture());
                marker.setTag(model.getId());
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }
    private void loadMarkerIcon(final Marker marker,String url) {
        Glide.with(this).load(url)
                .asBitmap().fitCenter().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {

                if (bitmap != null) {
                    // Create a rounded corners bitmap
                    Bitmap mBitmap = getRoundedBitmap(bitmap,15);
                    mBitmap = addBorderToRoundedBitmap(mBitmap,15, 10, Color.WHITE);
                    mBitmap = addBorderToRoundedBitmap(mBitmap, 15, 3, Color.LTGRAY);
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(mBitmap);
                    marker.setIcon(icon);
                }

            }
        });
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
    protected Bitmap getRoundedBitmap(Bitmap srcBitmap, int cornerRadius) {
        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                srcBitmap.getWidth(), // Width
                srcBitmap.getHeight(), // Height

                Bitmap.Config.ARGB_8888 // Config
        );
        Canvas canvas = new Canvas(dstBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the circular bitmap
        return dstBitmap;
    }

    // Custom method to add a border around rounded bitmap
    protected Bitmap addBorderToRoundedBitmap(Bitmap srcBitmap, int cornerRadius, int borderWidth, int borderColor){
        // We will hide half border by bitmap
        borderWidth = borderWidth*2;

        // Initialize a new Bitmap to make it bordered rounded bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                srcBitmap.getWidth() + borderWidth, // Width
                srcBitmap.getHeight() + borderWidth, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);

        // Initialize a new Paint instance to draw border
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        paint.setAntiAlias(true);

        // Initialize a new Rect instance
        Rect rect = new Rect(
                borderWidth/2,
                borderWidth/2,
                dstBitmap.getWidth() - borderWidth/2,
                dstBitmap.getHeight() - borderWidth/2
        );

        // Initialize a new instance of RectF;
        RectF rectF = new RectF(rect);

        // Draw rounded rectangle as a border/shadow on canvas
        canvas.drawRoundRect(rectF,cornerRadius,cornerRadius,paint);

        // Draw source bitmap to canvas
        canvas.drawBitmap(srcBitmap, borderWidth / 2, borderWidth / 2, null);
        srcBitmap.recycle();

        return dstBitmap;
    }
}

