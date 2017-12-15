package com.touristhan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import Utils.Session;

public class Dashboard extends AppCompatActivity {
    Button nearby_map_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_dashboard);
        startService(new Intent(Dashboard.this, LocationUpdater.class));
        init();
}

    private void init() {
        nearby_map_button = (Button) findViewById(R.id.find_friend);
        nearby_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, MapsActivity.class));
            }
        });
    }

    public void openTripAvisor(View view) {
        Intent ota = new Intent(this, tripAdvisorActivity.class);
        startActivity(ota);
    }

    public void openChatbot(View view) {
        Intent chatbot = new Intent(this, chatbot.class);
        startActivity(chatbot);
    }

    public void openfindplaces(View view) {
        Intent ofp = new Intent(this, findplaces.class);
        startActivity(ofp);
    }

    public void openSos(View view) {
        Intent sos = new Intent(this, sos.class);
        startActivity(sos);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void logout(View view) {
        FacebookSdk.sdkInitialize(getApplicationContext());
                // Create a confirmation dialog
                String logout = getResources().getString(com.facebook.R.string.com_facebook_loginview_log_out_action);
                String cancel = getResources().getString(com.facebook.R.string.com_facebook_loginview_cancel_action);
                String message;
                Profile profile = Profile.getCurrentProfile();
                if (profile != null && profile.getName() != null) {
                    message = String.format(
                            getResources().getString(
                                    com.facebook.R.string.com_facebook_loginview_logged_in_as),
                            profile.getName());
                } else {
                    message = getResources().getString(
                            com.facebook.R.string.com_facebook_loginview_logged_in_using_facebook);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(message)
                        .setCancelable(true)
                        .setPositiveButton(logout, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                LoginManager.getInstance().logOut();
                                Session.getSession(Dashboard.this).setUserId("");
                                startActivity(new Intent(Dashboard.this,LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton(cancel, null);
                builder.create().show();
    }

}
