package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrearNotificacionadmin extends AppCompatActivity {

    Button btn_volveradmin;
    Button btn_addnoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_notificacionadmin);

        this.setTitle( "Crear notificacion");

        btn_volveradmin = findViewById(R.id.btn_volveradmin);
        btn_volveradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Adminnoti.class);
                startActivity(intent);

            }
        });

    }


}