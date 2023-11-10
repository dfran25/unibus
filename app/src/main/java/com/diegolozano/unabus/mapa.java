package com.diegolozano.unabus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;

    Button btncerrarsecion, btnnotificacion;

    List<Lugar> lugares = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btncerrarsecion = findViewById(R.id.btn_cerrar_secion);
        btnnotificacion = findViewById(R.id.btn_notificaciones);


        btncerrarsecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnnotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotificacionUserActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("coordenadas123").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    mMap.setOnMapClickListener(mapa.this);
                    mMap.setOnMapLongClickListener(mapa.this);

                    // Crear una lista de lugares con sus coordenadas


                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Coordenada coordenada = documentSnapshot.toObject(Coordenada.class);
                        Lugar newLugar = new Lugar(coordenada.getDireccion(), new LatLng(coordenada.getLatitud(), coordenada.getLongitud()));
                        lugares.add(newLugar);
                    }

                    // Agregar marcadores para todos los lugares
                    for (Lugar lugar : lugares) {
                        mMap.addMarker(new MarkerOptions()
                                .position(lugar.getCoordenadas())
                                .title(lugar.getNombre()));
                    }

                    // Configurar el listener para manejar el clic en los marcadores
                    mMap.setOnMarkerClickListener(mapa.this);

                    // Ajustar la cámara para mostrar todos los marcadores
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Lugar lugar : lugares) {
                        builder.include(lugar.getCoordenadas());
                    }
                    LatLngBounds bounds = builder.build();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));


                }
            }
        });






    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        txtLatitud.setText(marker.getTitle());
        txtLongitud.setText(marker.getPosition().toString());
        return false;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        // Implementa la lógica de clic en el mapa si es necesario
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        // Implementa la lógica de clic largo en el mapa si es necesario
    }
}