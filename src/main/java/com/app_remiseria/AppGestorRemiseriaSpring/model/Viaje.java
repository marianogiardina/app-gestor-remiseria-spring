package com.app_remiseria.AppGestorRemiseriaSpring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double kilometros;
    private double valorKm;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estadoViaje;

    @ManyToOne
    @JoinColumn(name = "chofer_id", referencedColumnName = "id")
    private Chofer chofer;

    private String origen;
    private String destino;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
