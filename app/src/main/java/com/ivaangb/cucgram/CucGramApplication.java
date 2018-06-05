package com.ivaangb.cucgram;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by ivang on 5/6/2018.
 */

public class CucGramApplication extends Application {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseStorage firebaseStorage;



    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseCrash.log("Inicializando variables en CUCGramApplication");

        FacebookSdk.sdkInitialize(getApplicationContext());


        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w("PhotoCUCApp","Usuario logueado" +firebaseUser.getEmail());
                }else {
                    Log.w("PhotoCUCApp","Usuario no logueado");
                }
            }
        };

        firebaseStorage = FirebaseStorage.getInstance();

    }

    public StorageReference getStorageReference(){
        return firebaseStorage.getReference();
    }

}
