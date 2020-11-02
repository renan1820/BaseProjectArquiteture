package com.git.renan.baseprojectarquiteture.di.components;

import com.git.renan.baseprojectarquiteture.MainActivity;
import com.git.renan.baseprojectarquiteture.di.modules.ContextModule;
import com.git.renan.baseprojectarquiteture.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
