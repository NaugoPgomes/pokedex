package com.example.consultarpokemon;

import com.example.consultarpokemon.Model.pokemonAnswer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Get
{
    @GET("pokemon")
    Call<pokemonAnswer> getPokemonList();
}
