package com.ivaangb.cucgram.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ivaangb.cucgram.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class NewPostActivity extends AppCompatActivity {
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgPhoto = findViewById(R.id.imgPhoto);

        if (getIntent().getExtras() != null){
            String photoPath = getIntent().getExtras().getString("PHOTO_PATH_TEMP");

            Picasso.get().load((photoPath)).resize(2200,2200).into(imgPhoto);
        }

    }
}
