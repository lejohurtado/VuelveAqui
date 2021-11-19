package com.example.vuelveaqui.data.model;

public class User {
    private long id;
    private String nombres;
    private long telefono;
    private String correo;
    private String contrasena;

    public User(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public User(String nombres, long telefono, String correo, String contrasena) {
        this.nombres = nombres;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", telefono=" + telefono +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
