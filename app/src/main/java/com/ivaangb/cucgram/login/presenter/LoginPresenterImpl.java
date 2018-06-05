package com.ivaangb.cucgram.login.presenter;

import android.app.Activity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.login.interactor.LoginInteractor;
import com.ivaangb.cucgram.login.interactor.LoginInteractorImpl;
import com.ivaangb.cucgram.login.view.LoginView;

/**
 * Created by ivang on 5/6/2018.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        loginView.disbaleInputs();
        loginView.showProgressBar();

        interactor.sigIn(username, password, activity, firebaseAuth);
    }


    @Override
    public void loginSuccess() {
        loginView.goAccount();


        loginView.hideProgressBar();
    }

    @Override
    public void loginError(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();

        loginView.loginError(error);
    }
}
