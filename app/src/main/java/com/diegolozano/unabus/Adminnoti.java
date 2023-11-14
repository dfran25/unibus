package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Adminnoti extends AppCompatActivity {

    Button btn_irnoti;
    Button btn_irruta;
    Button btn_irparada;

    Button btn_cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminnoti);

        btn_irnoti = findViewById(R.id.btn_notinot);

        btn_irnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearNotificacionadmin.class);
                startActivity((intent));
            }
        });
        btn_irparada = findViewById(R.id.btn_notiparada);

        btn_irparada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CrearNotificacionparada.class);
                startActivity((intent));
            }
        });

        btn_irruta=findViewById(R.id.btn_notiruta);
        btn_irruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent((getApplicationContext()),CrearNotificacionruta.class);
                startActivity((intent));
            }
        });

        btn_cerrar=findViewById(R.id.btn_cerrar);

        btn_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}