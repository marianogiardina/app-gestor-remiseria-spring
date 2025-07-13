package com.app_remiseria.AppGestorRemiseriaSpring.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double kilometros;
    private double valorKm;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estadoViaje;

    @OneToOne
    @JoinColumn(name = "chofer_id", referencedColumnName = "id")
    private Chofer chofer;

    private String origen;
    private String destino;
    private LocalDateTime fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public double getValorKm() {
        return valorKm;
    }

    public void setValorKm(double valorKm) {
        this.valorKm = valorKm;
    }

    public EstadoViaje getEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(EstadoViaje estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
