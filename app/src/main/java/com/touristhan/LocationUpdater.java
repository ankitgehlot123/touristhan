package com.touristhan;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Utils.Session;
import Utils.Utils;


public class LocationUpdater extends Service implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    Context context;
    LocationRequest mLocationRequest;

    private static final int MILLISECONDS_PER_SECOND = 1000;

    public static final int UPDATE_INTERVAL_IN_SECONDS = 30;
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

    private static final int FASTEST_INTERVAL_IN_SECONDS = 10;
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;
    Session session;

    public LocationUpdater() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = LocationUpdater.this;
        session = Session.getSession(context);
        try{
            int off = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            if(off==0){
//                Intent onGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                onGPS.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(onGPS);
                stopSelf();
            } else {
                buildGoogleApiClient();
                mGoogleApiClient.connect();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }


        return super.onStartCommand(intent, flags, startId);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onConnected(Bundle bundle) {
        try{
//            mLastLocation = LocationServices.FusedLocationApi
//                    .getLastLocation(mGoogleApiClient);


            mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(UPDATE_INTERVAL);
//            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

           LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        Log.wtf("ankit","accuracy "+location.getAccuracy());
                        int suitableMeter = 100; // adjust your need
//                        if (location.hasAccuracy()  && location.getAccuracy() <= suitableMeter) {
                            // This is your most accurate location.
                          // update this location to firebase
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            Utils.location = location;

                            DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
                            mRootRef.child(Utils.userTable).child(session.getUserId()).child("latitude").setValue(latitude);
                            mRootRef.child(Utils.userTable).child(session.getUserId()).child("longitude").setValue(longitude);

                            stopSelf();
//                        }

                    }


                }
            });

        }catch (SecurityException ex){
            ex.printStackTrace();
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mGoogleApiClient.connect();
    }

}
