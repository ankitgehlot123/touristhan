package com.touristhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class sos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        startService(new Intent(getApplicationContext(), LockService.class));

    }
    public void toggleSOS(View view){
        stopService(new Intent(getApplicationContext(), LockService.class));
    }

}
