package com.example.ie_project;

import android.app.Application;

public class JettApplication extends Application {
    private static JettApplication instance;
    public static JettApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }


}
