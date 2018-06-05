package com.ivaangb.cucgram.login.presenter;

import android.view.View;

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
    public void signIn(String username, String password) {
        loginView.disbaleInputs();
        loginView.showProgressBar();

        interactor.sigIn(username, password);
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
