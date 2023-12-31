package com.diegolozano.unabus;

public class Usuario {
    private String id;
    private String nombre;
    private String apellido;
    private String correo;

    public Usuario() {
    }

    public Usuario( String nombre, String apellido, String correo) {
        this.id = "";
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
