package com.git.renan.baseprojectarquiteture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.git.renan.baseprojectarquiteture.databinding.ActivityMainBinding;
import com.git.renan.baseprojectarquiteture.model.UserModel;
import com.git.renan.baseprojectarquiteture.viewmodel.UserViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private UserViewModel userViewModel;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);

        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);

        userViewModel.getModelMutableLiveData().observe(this, userModel -> {
            activityMainBinding.setUser(userModel);
            Log.d(TAG, "onChanged: " + userModel.getPhone());
        });

        userViewModel.getIsLoading().observe(this, aBoolean -> {
            activityMainBinding.progress.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
        });

        userViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}