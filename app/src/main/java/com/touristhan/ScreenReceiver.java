package com.touristhan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
public class ScreenReceiver extends BroadcastReceiver {
    public static boolean wasScreenOn = true;
    int i=0;
    private long startMillis=0;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        long time= System.currentTimeMillis();
        Log.e("LOB","onReceive");
        if (startMillis==0 || (time-startMillis> 3000) ) {
            startMillis=time;
            i=1;
        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            wasScreenOn = false;
            i++;
            Log.e("LOB","wasScreenOn"+wasScreenOn);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            wasScreenOn = true;
            i++;

        }else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.e("LOB","userpresent");
            Log.e("LOB","wasScreenOn"+wasScreenOn);
            if(i==3){

                Intent call_intent=new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel","100",null));
                context.startActivity(call_intent);
            }
        }

    }
}
