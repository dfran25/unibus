package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ParadaUserActivity extends AppCompatActivity {

    private String[] localDataSet = new String[4];
    NotificacionAdapter myAdapter;
    RecyclerView rvParada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion_user);
        cargarfackedata();
        myAdapter = new NotificacionAdapter(localDataSet);
        rvParada = findViewById(R.id.rv_parada);

        rvParada.setAdapter(myAdapter);
        rvParada.setLayoutManager(new GridLayoutManager(this,2));

    }

    private void cargarfackedata() {
        localDataSet[0]="Parada 1";
        localDataSet[1]="Parada 2";
        localDataSet[2]="Parada 3";
    }
}