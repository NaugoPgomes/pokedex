package com.example.consultarpokemon.Retrofit;

import com.example.consultarpokemon.Model.pokemonAnswer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Get
{
    @GET("pokemon/?limit=151&offset=0")
    Call<pokemonAnswer> getPokemonList();
}
