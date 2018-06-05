package com.ivaangb.cucgram.post.view;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.adapter.PictureAdapterRecyclerView;
import com.ivaangb.cucgram.model.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int REQUEST_CAMERA = 1;
    private FloatingActionButton fabCamera;
    private String photoPathTemp = "";
    private String PackageName;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView picturesRecycler = view.findViewById(R.id.pictureRecycler);
        fabCamera = view.findViewById(R.id.fabCamera);

        PackageName = getActivity().getPackageName();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(buildPictures(),
                R.layout.cardview_picture, getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {

        Intent intentTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentTakePicture.resolveActivity(getActivity().getPackageManager()) != null){

            File photoFile = null;

            try {
                photoFile = createImageFile();


            }catch (Exception e){
                e.printStackTrace();
            }

            if (photoFile != null ){


                Uri photoUri = FileProvider.getUriForFile(getActivity(), PackageName, photoFile);

                intentTakePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                startActivityForResult(intentTakePicture, REQUEST_CAMERA);
            }

        }

    }

    private File createImageFile() throws IOException {

        String timeStamp = new String("yyyyMMdd_HH-mm-ss".format(String.valueOf(new Date())));
        String imageFileName = "JPEG" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File photo = File.createTempFile(imageFileName, ".jpg", storageDir);

        photoPathTemp = "file:" + photo.getAbsolutePath();

        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == getActivity().RESULT_OK){
            Log.d("HomeFragment", "Camera OK!! : ");
            Intent i = new Intent(getActivity(), NewPostActivity.class);
            i.putExtra("PHOTO_PATH_TEMP", photoPathTemp);
            startActivity(i);
        }
    }

    public ArrayList<Picture> buildPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://jsequeiros.com/sites/default/files/imagen-cachorro-comprimir.jpg",
                "Iván González", "5 días", "4 Me Gusta"));

        pictures.add(new Picture("http://www.quo.es/var/quo/storage/images/naturaleza/que-muestra-en-realidad-esta-desgarradora-imagen-no-es-lo-que-piensas/1338293-1-esl-ES/que-muestra-en-realidad-esta-desgarradora-imagen-no-es-lo-que-piensas_full_landscape.jpg",
                "Daniel González", "9 días", "9 Me Gusta"));

        pictures.add(new Picture("https://i.blogs.es/fff2dc/google-imagenes/1366_2000.jpg",
                "Esteban González", "8 días", "1 Me Gusta"));

        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar =  view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }


}
