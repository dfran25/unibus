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


    private String[] localDataSetparada = new String[3];
    NotificacionAdapter myAdapterparada;
    RecyclerView rvParada;

    private String[] localDataSetruta = new String[3];
    NotificacionAdapter myAdapterruta;
    RecyclerView rvRuta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion_user);
        cargarfackedata();
        cargarfackedata1();
        cargarfackedata2();
        myAdapter = new NotificacionAdapter(localDataSet);
        rvNotificaciones = findViewById(R.id.rv_notificaciones);

        rvNotificaciones.setAdapter(myAdapter);
        rvNotificaciones.setLayoutManager(new GridLayoutManager(this,2));


        myAdapterparada = new NotificacionAdapter(localDataSetparada);
        rvParada = findViewById(R.id.rv_parada);

        rvParada.setAdapter(myAdapterparada);
        rvParada.setLayoutManager(new GridLayoutManager(this,2));

        myAdapterruta = new NotificacionAdapter(localDataSetruta);
        rvRuta = findViewById(R.id.rv_ruta);

        rvRuta.setAdapter(myAdapterruta);
        rvRuta.setLayoutManager(new GridLayoutManager(this,2));


    }

    private void cargarfackedata() {
        localDataSet[0]="Notificacion 1";
        localDataSet[1]="Notificacion 2";
        localDataSet[2]="Notificacion 3";
    }


    private void cargarfackedata1() {
        localDataSetparada[0]="Parada 1";
        localDataSetparada[1]="Parada 2";
        localDataSetparada[2]="Parada 3";
    }

    private void cargarfackedata2() {
        localDataSetruta[0]="ruta1";
        localDataSetruta[1]="ruta2";
        localDataSetruta[2]="ruta3";
    }
}