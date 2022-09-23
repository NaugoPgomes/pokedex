package com.example.consultarpokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.consultarpokemon.Adapter.Adapter;
import com.example.consultarpokemon.Model.pokemonAnswer;
import com.example.consultarpokemon.Model.pokemonModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexActivity extends AppCompatActivity
{

    private Retrofit retrofit;

    private Adapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData();

    }

    private void getData()
    {

        if(isConnectedToTheInternet() == true)
        {
            Get service = retrofit.create(Get.class);

            Call<pokemonAnswer> pokemonAnswerCall = service.getPokemonList();

            pokemonAnswerCall.enqueue(new Callback<pokemonAnswer>()
            {
                @Override
                public void onResponse(Call<pokemonAnswer> call, Response<pokemonAnswer> response)
                {

                    if(response.isSuccessful())
                    {
                        pokemonAnswer answer = response.body();

                        ArrayList<pokemonModel> pokemonList = answer.getResults();

                        adapter.addPokemonList(pokemonList);
                    }

                }

                @Override
                public void onFailure(Call<pokemonAnswer> call, Throwable t)
                {

                }
            });
        }
        else
        {
            Toast.makeText(this, "O Dispositivo não está conectado na Internet.", Toast.LENGTH_SHORT).show();
        }


    }

    private Boolean isConnectedToTheInternet()
    {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();


        if(netInfo == null)
        {
            return false;
        }

        return true;

    }

}