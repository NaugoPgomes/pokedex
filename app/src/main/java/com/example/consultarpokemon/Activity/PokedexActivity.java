package com.example.consultarpokemon.Activity;

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
import com.example.consultarpokemon.R;
import com.example.consultarpokemon.Retrofit.Get;
import com.example.consultarpokemon.Retrofit.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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


        getData();


    }

    private void getData()
    {

        if(isConnectedToTheInternet() == true)
        {

            RetrofitInstance instance = new RetrofitInstance();

            Get service = instance.getInstancia("https://pokeapi.co/api/v2/").create(Get.class);

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