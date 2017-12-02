package com.touristhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.Session;
import Utils.Utils;
import model.UserModel;


public class LoginActivity extends AppCompatActivity {
    public static String id, link, name;
    TextView textView;
    LoginButton loginButton;
    CallbackManager callbackManager;
    private DatabaseReference mRootRef= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef=mRootRef.child(Utils.userTable);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        textView = (TextView)findViewById(R.id.textView_Status);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);
//                        Log.wtf("ankit",""+object);
//                        Log.wtf("ankit",""+response.getRawResponse());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,link,name");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel Login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.wtf("ankit",""+error.getMessage());
                Log.wtf("ankit",""+error.getCause());
                Toast.makeText(getApplicationContext(), "Error Login", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayUserInfo(JSONObject object){

        try {
            id = object.getString("id");
            link = object.getString("link");
            name = object.getString("name");

            UserModel model = new UserModel();
            model.setId(id);
            model.setLink(link);
            model.setName(name);
            model.setLatitude("");
            model.setLongitude("");

            DatabaseReference mUserRef=mUsersRef.child(id);
            mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try{
                        Log.wtf("ankit",dataSnapshot.getValue().toString());
                        String data_json = dataSnapshot.getValue().toString();
                        data_json = data_json.replace("=", "=\"");
                        data_json = data_json.replace(",", "\",");
                        data_json = data_json.replace("}", "\"}");
                        Log.wtf("ankit",data_json);

                        JSONObject jobj = new JSONObject(data_json);
                        String id = jobj.getString("id");

                        Session.getSession(LoginActivity.this).setUserId(id);
                        Log.wtf("ankit",""+jobj);

                        startActivity(new Intent(LoginActivity.this,Dashboard.class));
                        finish();

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    showToast(databaseError.getMessage());
                }
            });
            mUserRef.setValue(model);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message){
        Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();
    }

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void openGuide(View view)
    {
        Intent og = new Intent(this, guide.class);
        startActivity(og);
    }
}