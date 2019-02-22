package com.jetec.jtc_ble.SupportFunction;

import android.util.Log;

public class LogMessage {

    public LogMessage(){
        super();
    }

    public void showmessage(String TAG, String str){
        Log.d(TAG,str);
    }
}