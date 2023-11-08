package com.diegolozano.unabus;

import com.google.android.gms.maps.model.LatLng;

public class Lugar {
    private String nombre;
    private LatLng coordenadas;

    public Lugar(String nombre, LatLng coordenadas) {
        this.nombre = nombre;
        this.coordenadas = coordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }
}
