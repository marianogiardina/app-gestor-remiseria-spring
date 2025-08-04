package com.app_remiseria.AppGestorRemiseriaSpring.model;

import jakarta.persistence.*;

@Entity
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long dni;
    private Long celular;
    private boolean autoPropio;
    private boolean disponible;

    @OneToOne
    @JoinColumn(name = "auto_id", referencedColumnName = "id")
    private Auto autoAlquilado;

    //Para que no haya errores cuando se elimina un chofer, agrego un atributo eliminado, para hacer un soft delete
    @Column(name = "eliminado", nullable = false)
    private boolean eliminado = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
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
