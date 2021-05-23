package com.mahad.task.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mahad.task.MainActivity;
import com.mahad.task.R;
import com.mahad.task.adapter.Favourite_Adapter;
import com.mahad.task.model.Fav_Model;
import com.mahad.task.roomdb.Fav_Entities;

import java.util.ArrayList;
import java.util.List;


public class Favorite_frag extends Fragment {

    RecyclerView recyclerView;
    List<Fav_Model> modelList;
    Favourite_Adapter favouriteAdapter;

    Toolbar toolbar;
    TextView toolbar_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        toolbar = view.findViewById(R.id.toolbar_main);
        toolbar_txt = toolbar.findViewById(R.id.toolbar_txt_view);
        toolbar_txt.setText(getString(R.string.favourite));
        //toolbar

        recyclerView = view.findViewById(R.id.recycle_view_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        modelList = new ArrayList<>();
        //init

        List<Fav_Entities> favModelList = MainActivity.dataBase_main.dao().fetch();
        for (Fav_Entities entities : favModelList) {
            modelList.add(new Fav_Model(
                    entities.getImageURL_e(),
                    entities.getGlassName_e(),
                    entities.getDrinkDetail_e(),
                    entities.isAlcoholic_e()
            ));
        }

        favouriteAdapter = new Favourite_Adapter(modelList);
        recyclerView.setAdapter(favouriteAdapter);

        return view;
    }
}