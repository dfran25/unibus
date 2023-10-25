package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void irRegistro(View vista){

        Intent myIntent = new Intent(this,Registrar.class);
        startActivity(myIntent);

    }

    public void irMapa(View vista){
        Intent myIntent = new Intent(this,Mapa.class);
        startActivity(myIntent);
    }
}