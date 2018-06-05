package com.ivaangb.cucgram.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.adapter.PictureAdapterRecyclerView;
import com.ivaangb.cucgram.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(R.id.searchFragment);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);

        picturesRecycler.setLayoutManager(gridLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buildPictures(), R.layout.cardview_picture, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
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

}