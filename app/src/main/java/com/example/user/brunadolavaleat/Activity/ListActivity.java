package com.example.user.brunadolavaleat.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.brunadolavaleat.Activity.Entities.Api;
import com.example.user.brunadolavaleat.Activity.Entities.Hero;
import com.example.user.brunadolavaleat.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView  listView = findViewById(R.id.listView);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api api = retrofit.create(Api.class);

        Call<Hero> call = api.getHeroes();

        call.enqueue(new Callback<Hero>() {
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {

                Hero hero = response.body();
//                List<Hero> heroes = response.body();
//
//                String[] heroNames = new String[heroes.size()];
//
//                for (int i = 0; i < heroes.size(); i++){
//                    heroNames[i] = heroes.get(i).getName();
//                }
//
//                listView.setAdapter(
//                        new ArrayAdapter<String>(
//                                getApplicationContext(),
//                                android.R.layout.simple_list_item_1, heroNames
//                        )
//                );

            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
