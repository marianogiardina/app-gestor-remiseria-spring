package com.app_remiseria.AppGestorRemiseriaSpring.model;

import jakarta.persistence.*;

@Entity
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int dni;
    private long celular;
    private boolean autoPropio;
    private boolean disponible;

    @OneToOne
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto autoAlquilado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public boolean isAutoPropio() {
        return autoPropio;
    }

    public void setAutoPropio(boolean autoPropio) {
        this.autoPropio = autoPropio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Auto getAutoAlquilado() {
        return autoAlquilado;
    }

    public void setAutoAlquilado(Auto autoAlquilado) {
        this.autoAlquilado = autoAlquilado;
    }
}
