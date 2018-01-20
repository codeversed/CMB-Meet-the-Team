package com.codeversed.meettheteam;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by steve on 1/19/18.
 * steve.albright@gmail.com
 */
public class MyApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    // Setup Timber DebugTree if debug build
    if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());

  }

}
