package com.ivaangb.cucgram.login.view;

import android.view.View;

/**
 * Created by ivang on 5/6/2018.
 */

public interface LoginView {

    void enableInputs();
    void disbaleInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);

    void goCreateAccount();
    void goAccount();

}
