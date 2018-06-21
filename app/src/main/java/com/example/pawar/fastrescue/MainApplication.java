package com.example.pawar.fastrescue;

import android.app.Application;

import com.example.pawar.fastrescue.manager.Contextor;

/**
 * Created by pawar on 24-Feb-17.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
