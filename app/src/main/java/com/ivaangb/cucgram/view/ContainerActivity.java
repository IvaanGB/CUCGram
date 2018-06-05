package com.ivaangb.cucgram.view;

import android.support.v4.app.Fragment;;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.post.view.HomeFragment;
import com.ivaangb.cucgram.view.fragment.ProfileFragment;
import com.ivaangb.cucgram.view.fragment.SearchFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class ContainerActivity extends AppCompatActivity {
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

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

}
