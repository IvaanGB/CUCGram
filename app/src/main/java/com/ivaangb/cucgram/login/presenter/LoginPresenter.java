package com.ivaangb.cucgram.login.presenter;

/**
 * Created by ivang on 5/6/2018.
 */

public interface LoginPresenter {

    void signIn(String username, String password);
    void loginSuccess();
    void loginError(String error);

}
