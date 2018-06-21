package com.example.pawar.fastrescue.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.pawar.fastrescue.activity.AlertActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {



    }

    @Override
    public void onCreate() {
        SharedPreferences sp = getSharedPreferences("CHECK_NOTI", Context.MODE_PRIVATE);
        Boolean check = sp.getBoolean("first_data", true);
        if (check == true) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first_data", false);
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), AlertActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);
        } else {
            int count = sp.getInt("count_noti", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("count_noti", count+1);
            editor.commit();

        }
    }

}

