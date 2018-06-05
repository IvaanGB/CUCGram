package com.ivaangb.cucgram.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ivang on 5/6/2018.
 */

public interface LoginInteractor {

    void sigIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);

}
