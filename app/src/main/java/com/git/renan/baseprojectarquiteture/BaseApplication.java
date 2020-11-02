package com.git.renan.baseprojectarquiteture;

import android.app.Application;

import com.git.renan.baseprojectarquiteture.di.components.AppComponent;
import com.git.renan.baseprojectarquiteture.di.components.DaggerAppComponent;

public class BaseApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
