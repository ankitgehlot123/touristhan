package com.touristhan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.Dash;

import Utils.Session;

public class SplashActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public static final int permsRequestCode = 200;
    GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(shouldAskPermission()){

            if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(),
                    android.Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED)) {
                // permission is ok do rest of the work
                init();
            } else {
                // permission is not ok show error
                String[] perms = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.ACCESS_FINE_LOCATION"
                        ,"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_WIFI_STATE","android.permission.WRITE_CALENDAR","android.permission.READ_CALENDAR"};
                givePermissions(perms);
            }

        } else {
            init();
        }
    }

    private void init(){



        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(Session.getSession(SplashActivity.this).getUserId().equalsIgnoreCase("")) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                } else {
                  startActivity(new Intent(SplashActivity.this,Dashboard.class));

                    finish();
                }
            }
        }.start();


    }

    @SuppressLint("NewApi")
    private void givePermissions(String[] perms) {
        requestPermissions(perms, permsRequestCode);
    }

    // should ask permissions
    private boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case permsRequestCode: {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED)) {
                    // permission is ok do rest of the work
                    init();
                } else {
                    Toast.makeText(SplashActivity.this,"You do not have sufficient permissions",Toast.LENGTH_LONG).show();
                    String[] perms = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.ACCESS_FINE_LOCATION"
                            ,"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_WIFI_STATE","android.permission.WRITE_CALENDAR","android.permission.READ_CALENDAR"};
                    givePermissions(perms);
                }

            }

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1000&&resultCode==-1){
            init();
        } else {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
