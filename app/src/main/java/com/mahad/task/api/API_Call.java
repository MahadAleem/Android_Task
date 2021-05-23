package com.mahad.task.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Call {

    String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";


    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();


    @GET("search.php")
        //?s=
    Call<JsonObject> getByName(@Query("s") String name);

    @GET("search.php")
        //?f=
    Call<JsonObject> getByAlphabets(@Query("f") String alphabet);
}
