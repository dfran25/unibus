package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Registrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }
    public void irLogin(){

        Intent myIntent = new Intent(this,MainActivity.class);
        startActivity(myIntent);
    }
}