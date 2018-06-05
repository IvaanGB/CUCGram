package com.ivaangb.cucgram.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.login.view.CreateAccountActivity;
import com.ivaangb.cucgram.login.view.PrincipalLogin;
import com.ivaangb.cucgram.post.view.HomeFragment;
import com.ivaangb.cucgram.view.fragment.ProfileFragment;
import com.ivaangb.cucgram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {
    private static final String TAG = "ContainerActivity";
    private BottomBar bottomBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        firebaseInitialize();

        bottomBar = findViewById(R.id.bottombar);
        bottomBar.setDefaultTab(R.id.home);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.home:
                        addFragment(new HomeFragment());

                        break;

                    case R.id.profile:
                        addFragment(new ProfileFragment());
                        break;

                    case R.id.search:
                        addFragment(new SearchFragment());
                        break;

                }
            }
        });

    }

    private void addFragment(Fragment fra){
        if (null != fra){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fra)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void firebaseInitialize(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if (firebaseUser != null){
                    Log.w(TAG,"Usuario logueado" +firebaseUser.getEmail());
                }else {
                    Log.w(TAG,"Usuario no logueado");
                }

            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_opciones, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mSignOut:

                firebaseAuth.signOut();

                if (LoginManager.getInstance() != null) {
                    LoginManager.getInstance().logOut();
                }
                    Toast.makeText(this, "Sesión Cerrada",
                            Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ContainerActivity.this, PrincipalLogin.class);
                startActivity(i);
                break;

            case R.id.mAbout:
                Toast.makeText(this, "PhotoCUC by Ivan Gonzalez",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
