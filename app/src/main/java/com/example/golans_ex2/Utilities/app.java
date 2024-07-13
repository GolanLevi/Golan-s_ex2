package com.example.golans_ex2.Utilities;

import android.app.Application;

import com.example.golans_ex2.Utilities.SharePreferencesManager;

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharePreferencesManager.init(this);
        //SignalManager.init(this);
    }
}
