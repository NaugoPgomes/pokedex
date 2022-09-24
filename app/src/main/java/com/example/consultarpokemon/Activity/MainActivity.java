package com.example.consultarpokemon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.consultarpokemon.R;

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