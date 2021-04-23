package com.example.haltinghunger;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(FoodPost.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HoA6v7s5ui2x9oABbQUpapT9Csf1Xm2juv5d3Y9h")
                .clientKey("0y1HKsVmjrt5OhfRnxYnKgNiOVCjxOeKD6KU8eEh")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
