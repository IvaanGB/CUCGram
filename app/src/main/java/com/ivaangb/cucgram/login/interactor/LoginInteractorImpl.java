package com.ivaangb.cucgram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.ivaangb.cucgram.login.presenter.LoginPresenter;
import com.ivaangb.cucgram.login.repository.LoginRepository;
import com.ivaangb.cucgram.login.repository.LoginRespositoryImpl;

/**
 * Created by ivang on 5/6/2018.
 */

public class LoginInteractorImpl implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImpl(LoginPresenter presenter)
    {
        this.presenter = presenter;
        repository = new LoginRespositoryImpl(presenter);
    }

    @Override
    public void sigIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        repository.signIn(username, password, activity, firebaseAuth);
    }
}
