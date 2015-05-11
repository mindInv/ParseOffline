package com.mi.parseoffline;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by punit on 21/4/15.
 */
public class AppClass extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);// Enable Crash Reporting


        Parse.initialize(this, "12345798", "6549712");
    }
}
