package com.ivaangb.cucgram.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ivang on 5/6/2018.
 */

public interface LoginRepository {

    void signIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);

}
