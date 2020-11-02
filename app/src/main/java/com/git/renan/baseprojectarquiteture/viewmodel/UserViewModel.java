package com.git.renan.baseprojectarquiteture.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.git.renan.baseprojectarquiteture.model.UserModel;
import com.git.renan.baseprojectarquiteture.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    public UserRepository userRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<UserModel> modelMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public MutableLiveData<UserModel> getModelMutableLiveData(){
        loadData();
        return modelMutableLiveData;
    }

    private void loadData(){
        isLoading.setValue(true);
        disposable.add(userRepository.modelSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    @Override
                    public void onSuccess(@NonNull UserModel userModel) {
                        isLoading.setValue(false);
                        modelMutableLiveData.setValue(userModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        isLoading.setValue(false);
                        error.setValue(e.getMessage());
                    }
                }));
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
