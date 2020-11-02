package com.git.renan.baseprojectarquiteture.remote;

import com.git.renan.baseprojectarquiteture.model.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserService {

    @GET("/users/2")
    Single<UserModel> getUserModel();
}
