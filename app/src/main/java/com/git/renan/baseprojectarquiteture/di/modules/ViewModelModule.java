package com.git.renan.baseprojectarquiteture.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.git.renan.baseprojectarquiteture.di.ViewModelKey;
import com.git.renan.baseprojectarquiteture.viewmodel.UserViewModel;
import com.git.renan.baseprojectarquiteture.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindViewModel(UserViewModel userViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);

}
