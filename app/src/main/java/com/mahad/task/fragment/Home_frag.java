package com.mahad.task.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.mahad.task.MainActivity;
import com.mahad.task.R;
import com.mahad.task.adapter.Home_Adapter;
import com.mahad.task.api.API_Call;
import com.mahad.task.model.Home_Model;
import com.mahad.task.roomdb.Fav_Entities;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_frag extends Fragment {

    public static final String SHARED_PREFERENCES_NAME = "MPREFERENCES";

    private View view;
    private RadioGroup radioGroupV;
    private SearchView searchView;

    RecyclerView recyclerView;
    List<Home_Model> homeModelList;

    Home_Adapter home_adapter;

    private API_Call apiCallInterface;

    Call<JsonObject> apiCallInterfaceByName;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ProgressDialog progressDialog;


    boolean isname;
    RadioButton radioButton_name, radioButton_alpha;


    @SuppressLint({"NonConstantResourceId", "CommitPrefEdits"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init(); // init view

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeModelList = new ArrayList<>();
        apiCallInterface = API_Call.retrofit.create(API_Call.class);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Progress Dialog");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        //shared refrence
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //end

        //radio group
        radioGroupV.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio_name:
                    isname = true;
                    editor.putBoolean("name", isname);
                    //
                    break;
                case R.id.radio_alphabet:
                    isname = false;
                    editor.putBoolean("name", isname);
                    // ;
                    break;
            }
            editor.apply();
        });
        //end

        home_adapter = new Home_Adapter(getContext(), homeModelList, new Home_Adapter.ClickListner() {
            @Override
            public void favBtnAdd(int position, View view) {

                Fav_Entities fav_entities = new Fav_Entities();
                fav_entities.setImageURL_e(homeModelList.get(position).getImageURL_m());
                fav_entities.setDrinkDetail_e(homeModelList.get(position).getDrinkDetail_m());
                fav_entities.setGlassName_e(homeModelList.get(position).getGlassName_m());
                fav_entities.setAlcoholic_e(homeModelList.get(position).isAlcoholic());

                MainActivity.dataBase_main.dao().insert(fav_entities);
            }
        });


        recyclerView.setAdapter(home_adapter);

        setupSearchView(); //search view method

        return view;
    }

    void init() {
        radioGroupV = view.findViewById(R.id.radioGroup);
        searchView = view.findViewById(R.id.searchbar);
        recyclerView = view.findViewById(R.id.recycle_view_home);

        radioButton_name = view.findViewById(R.id.radio_name);
        radioButton_alpha = view.findViewById(R.id.radio_alphabet);
    }

    private void getValuesAndSet() {
        progressDialog.show();
        apiCallInterfaceByName.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        assert response.body() != null;
                        org.json.JSONObject obj = new JSONObject(response.body().toString());
                        org.json.JSONArray jsonArray = obj.getJSONArray("drinks");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Log.i("msg", "onResponse: " + object.get("strAlcoholic").toString());

                            homeModelList.add(new Home_Model(
                                    object.get("strDrinkThumb").toString(),
                                    object.get("strGlass").toString(),
                                    object.get("strInstructions").toString(),
                                    checkAlcoholic(object.get("strAlcoholic").toString())
                            ));
                            home_adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        Log.i("msg", "error: " + e.getLocalizedMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Log.i("msg", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getContext(), "Due to some reason request fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    void setupSearchView() {
        boolean is = sharedPreferences.getBoolean("name", true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (is) {
                    apiCallInterfaceByName = apiCallInterface.getByName(query);

                } else {
                    apiCallInterfaceByName = apiCallInterface.getByAlphabets(query);

                }
                getValuesAndSet();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homeModelList.clear();
                home_adapter.notifyDataSetChanged();
                return true;
            }

        });
    }

    boolean checkAlcoholic(String value) {
        return value.equals("Alcoholic");
    }


    @Override
    public void onResume() {
        super.onResume();
        if (sharedPreferences.getBoolean("name", true)) {
            radioButton_name.setChecked(true);
        } else {
            radioButton_alpha.setChecked(true);
        }
    }
}