package com.example.consultarpokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener
{


    ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        logo = findViewById(R.id.picturePokebola);
        logo.setOnClickListener(this);


    }


    @Override
    public void onClick(View view)
    {

        if(view == logo)
        {
            goToPokedex();
        }

    }

    private void goToPokedex()
    {
        Intent intentE = new Intent(this, PokedexActivity.class);
        startActivity(intentE);
    }
}