package com.diegolozano.unabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotificacionUserActivity extends AppCompatActivity {

    private String[] localDataSet = new String[0];
    private ArrayList<Notificacion> localDataSetN = new ArrayList<>();
    NotificacionAdapter myAdapter;
    RecyclerView rvNotificaciones;


    private String[] localDataSetparada = new String[0];

    private ArrayList<Parada>  localDataSetparadaN = new ArrayList<>();
    NotificacionAdapter myAdapterparada;
    RecyclerView rvParada;

    private String[] localDataSetruta = new String[0];

    private ArrayList<Ruta>  localDataSetrutaN = new ArrayList<>();
    NotificacionAdapter myAdapterruta;
    RecyclerView rvRuta;

    Button button2;


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
        rvNotificaciones.setLayoutManager(new GridLayoutManager(this, 2));


        myAdapterparada = new NotificacionAdapter(localDataSetparada);
        rvParada = findViewById(R.id.rv_parada);

        rvParada.setAdapter(myAdapterparada);
        rvParada.setLayoutManager(new GridLayoutManager(this, 2));

        myAdapterruta = new NotificacionAdapter(localDataSetruta);
        rvRuta = findViewById(R.id.rv_ruta);

        rvRuta.setAdapter(myAdapterruta);
        rvRuta.setLayoutManager(new GridLayoutManager(this, 2));


    }

    public void goBack(View view) {
        startActivity(new Intent(NotificacionUserActivity.this, mapa.class));
    }

    private void cargarfackedata() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("notificaciones").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot:task.getResult()){
                        Notificacion mynotiicacion = documentSnapshot.toObject(Notificacion.class);
                        localDataSetN.add(mynotiicacion);
                    }
                    String[] dataNotificaciones = new String[localDataSetN.size()];
                    int contador = 0;
                    for (Notificacion notificacion: localDataSetN){
                        dataNotificaciones[contador]=notificacion.getName();
                        contador+=1;
                    }
                    myAdapter.setLocalDataSet(dataNotificaciones);
                }

            }


        });
    }

        private void cargarfackedata1() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("paradas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot:task.getResult()){
                        Parada myparada = documentSnapshot.toObject(Parada.class);
                        localDataSetparadaN.add(myparada);
                    }
                    String[] dataParada = new String[localDataSetparadaN.size()];
                    int contador = 0;
                        for (Parada parada: localDataSetparadaN){
                        dataParada[contador]=parada.getName();
                        contador+=1;
                    }
                    myAdapterparada.setLocalDataSet(dataParada);
                }

            }


        });
    }

    private void cargarfackedata2() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("rutas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot:task.getResult()){
                        Ruta myruta = documentSnapshot.toObject(Ruta.class);
                        localDataSetrutaN.add(myruta);
                    }
                    String[] dataRuta = new String[localDataSetrutaN.size()];
                    int contador = 0;
                    for (Ruta ruta  : localDataSetrutaN){
                        dataRuta[contador]=ruta.getName();
                        contador+=1;
                    }
                    myAdapterruta.setLocalDataSet(dataRuta);
                }

            }


        });
    }

}