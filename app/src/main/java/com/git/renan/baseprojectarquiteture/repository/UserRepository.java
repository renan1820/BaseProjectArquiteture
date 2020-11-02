package com.git.renan.baseprojectarquiteture.repository;

import com.git.renan.baseprojectarquiteture.model.UserModel;
import com.git.renan.baseprojectarquiteture.remote.UserService;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {
    private UserService userService;

    @Inject
    public UserRepository(UserService userService){
        this.userService = userService;
    }

    public Single<UserModel> modelSingle(){
        return userService.getUserModel();
    }
}
