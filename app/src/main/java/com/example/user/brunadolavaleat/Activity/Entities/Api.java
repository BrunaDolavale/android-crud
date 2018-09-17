package com.example.user.brunadolavaleat.Activity.Entities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/marvel/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
