package com.app_remiseria.AppGestorRemiseriaSpring.dto;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;

public class SemanaChoferDto {


    private Chofer chofer;
    private Long cantidadViajes;
    private double cantidadKilometros;
    private double sueldoSemanal;

    public SemanaChoferDto(Chofer chofer, Long cantidadViajes, double cantidadKilometros, double sueldoSemanal) {
        this.chofer = chofer;
        this.cantidadViajes = cantidadViajes;
        this.cantidadKilometros = cantidadKilometros;
        this.sueldoSemanal = sueldoSemanal;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public Long getCantidadViajes() {
        return cantidadViajes;
    }

    public void setCantidadViajes(Long cantidadViajes) {
        this.cantidadViajes = cantidadViajes;
    }

    public double getCantidadKilometros() {
        return cantidadKilometros;
    }

    public void setCantidadKilometros(double cantidadKilometros) {
        this.cantidadKilometros = cantidadKilometros;
    }

    public double getSueldoSemanal() {
        return sueldoSemanal;
    }

    public void setSueldoSemanal(double sueldoSemanal) {
        this.sueldoSemanal = sueldoSemanal;
    }

}
