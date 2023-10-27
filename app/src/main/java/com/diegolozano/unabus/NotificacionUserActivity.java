package com.diegolozano.unabus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class NotificacionUserActivity extends AppCompatActivity {

    private String[] localDataSet = new String[3];
    NotificacionAdapter myAdapter;
    RecyclerView rvNotificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion_user);
        cargarfackedata();
        myAdapter = new NotificacionAdapter(localDataSet);
        rvNotificaciones = findViewById(R.id.rv_notificaciones);

        rvNotificaciones.setAdapter(myAdapter);
        rvNotificaciones.setLayoutManager(new GridLayoutManager(this,2));




    }

    private void cargarfackedata() {
        localDataSet[0]="Notificacion 1";
        localDataSet[1]="Notificacion 2";
        localDataSet[2]="Notificacion 3";
    }
}