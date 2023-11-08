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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;

    Button btncerrarsecion,btnnotificacion;


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
            public void onClick (View v){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
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
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        // Crear una lista de lugares con sus coordenadas
        List<Lugar> lugares = new ArrayList<>();
        lugares.add(new Lugar("Csu", new LatLng(7.112830264451631, -73.10531339503355)));
        lugares.add(new Lugar("Calle 56 Cra. 46", new LatLng(7.109972486470883, -73.10577979118273)));
        lugares.add(new Lugar("Calle 45 Cra. 55", new LatLng(7.111296728450606, -73.10605225441464)));
        lugares.add(new Lugar("Calle 56 Cra. 34", new LatLng(7.110122952762382, -73.10868075556954)));
        lugares.add(new Lugar("Clinica Bucaramanga", new LatLng(7.111494493991738, -73.10818082102548)));
        lugares.add(new Lugar("Gratamira", new LatLng(7.116097901716439, -73.10984321813247)));
        lugares.add(new Lugar("Parque San Pio", new LatLng(7.118723627045239, -73.10956450531071)));
        lugares.add(new Lugar("Campus Rafael Ardila Duarte", new LatLng(7.1220875794377365, -73.11086788169867)));
        lugares.add(new Lugar("Calle 48 Cra. 35A", new LatLng(7.117161658980827, -73.10929801550148)));
        lugares.add(new Lugar("Cra 38 Calle. 49", new LatLng(7.115975918125571, -73.10768383007851)));
        lugares.add(new Lugar("Unab Jardin", new LatLng(7.116840269614909, -73.10547391439393)));
        lugares.add(new Lugar("Puente peatonal UNAB-Av. El Jardin", new LatLng(7.11635450545716, -73.10430377070732)));

        // Agregar marcadores para todos los lugares
        for (Lugar lugar : lugares) {
            mMap.addMarker(new MarkerOptions()
                    .position(lugar.getCoordenadas())
                    .title(lugar.getNombre()));
        }

        // Configurar el listener para manejar el clic en los marcadores
        mMap.setOnMarkerClickListener(this);

        // Ajustar la cámara para mostrar todos los marcadores
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Lugar lugar : lugares) {
            builder.include(lugar.getCoordenadas());
        }
        LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));
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