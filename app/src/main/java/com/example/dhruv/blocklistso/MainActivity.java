package com.example.dhruv.blocklistso;

import java.lang.reflect.Method;
import com.android.internal.telephony.ITelephony;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity extends BroadcastReceiver {
    Context context = null;
    private static final String TAG = "Phone call";
    private ITelephony telephonyService;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "Receving....");
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.d(TAG, "num:: " + number);
        if (number.equals("+919871117535")) {
            TelephonyManager telephony = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Class c = Class.forName(telephony.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                telephonyService = (ITelephony) m.invoke(telephony);
                //telephonyService.silenceRinger();
                telephonyService.endCall();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}