package com.touristhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    Button nearby_map_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        startService(new Intent(Dashboard.this,LocationUpdater.class));
        init();
    }

    private void init(){
        nearby_map_button = (Button)findViewById(R.id.find_friend);
        nearby_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,MapsActivity.class));
            }
        });
    }
    public void openTripAvisor(View view)
    {
        Intent ota = new Intent(this, tripAdvisorActivity.class);
        startActivity(ota);
    }
    public void openChatbot(View view)
    {
        Intent chatbot = new Intent(this, chatbot.class);
        startActivity(chatbot);
    }
    public void openfindplaces(View view)
    {
        Intent ofp = new Intent(this, findplaces.class);
        startActivity(ofp);
    }
    public void openSos(View view)
    {
        Intent sos = new Intent(this, sos.class);
        startActivity(sos);
    }

}
