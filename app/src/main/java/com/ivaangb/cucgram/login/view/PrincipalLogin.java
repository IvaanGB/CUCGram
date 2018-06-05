package com.ivaangb.cucgram.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.login.presenter.LoginPresenter;
import com.ivaangb.cucgram.login.presenter.LoginPresenterImpl;
import com.ivaangb.cucgram.view.ContainerActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class PrincipalLogin extends AppCompatActivity implements LoginView {

    private TextInputEditText username, password;
    private Button btnLogin;
    private LoginButton facebook;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;

    private static final String TAG = "LoginRespositoryImpl";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();


        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG,"Usuario logueado" +firebaseUser.getEmail());
                    goAccount();
                }else {
                    Log.w(TAG,"Usuario no logueado");
                }
            }
        };

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        facebook = findViewById(R.id.login_facebook);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    signIn(username.getText().toString(),
                            password.getText().toString());


            }
        });

        facebook.setReadPermissions(Arrays.asList("email"));

        facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w(TAG, "Facebook login success" +loginResult.getAccessToken().getApplicationId());
                signInFacebookFirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.w(TAG, "Facebook login cancelado");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG, "Facebook login cancelado" + error.toString());
                error.printStackTrace();
            }
        });

    }

    private void signInFacebookFirebase(AccessToken accessToken) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = task.getResult().getUser();

                    SharedPreferences preferences = getSharedPreferences("USER",
                            Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", user.getEmail());
                    editor.commit();

                    goAccount();
                    Toast.makeText(PrincipalLogin.this, "Login Facebook Exitoso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PrincipalLogin.this, "Login Facebook NO Exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void signIn(String username, String password) {
        presenter.signIn(username, password, this, firebaseAuth);
    }

    public void goCreateAccount(View view){
        goCreateAccount();
    }




    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    @Override
    public void disbaleInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.errorLogin) + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goAccount() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
