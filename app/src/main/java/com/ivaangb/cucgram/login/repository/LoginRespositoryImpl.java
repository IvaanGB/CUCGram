package com.ivaangb.cucgram.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    public void signIn(String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {

        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(
                activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            FirebaseUser user = task.getResult().getUser();

                            SharedPreferences preferences = activity.getSharedPreferences("USER",
                                    Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("email", user.getEmail());
                            editor.commit();

                            presenter.loginSuccess();

                        }else {
                            presenter.loginError("Ocurrió un error");
                        }

                    }
                });

    }

}
