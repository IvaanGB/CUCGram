package com.ivaangb.cucgram.login.repository;

import android.content.res.Resources;

import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.login.presenter.LoginPresenter;

/**
 * Created by ivang on 5/6/2018.
 */

public class LoginRespositoryImpl implements LoginRepository {

    LoginPresenter presenter;

    public LoginRespositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password) {
        boolean success = true;
        if (success){

            presenter.loginSuccess();
        }else{
            presenter.loginError("Ocurrió un error");
        }
    }

}
