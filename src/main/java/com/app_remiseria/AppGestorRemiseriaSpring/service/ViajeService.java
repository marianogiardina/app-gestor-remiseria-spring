package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.dto.BalanceMensualDto;
import com.app_remiseria.AppGestorRemiseriaSpring.dto.SemanaChoferDto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.model.EstadoViaje;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Viaje;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ChoferRepository;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    public List<Viaje> findAll(){

        return viajeRepository.findAll();

    }

    public Viaje findById(Long id){
        return viajeRepository.findById(id).orElse(null);
    }

    public Viaje save(Viaje viaje){

        //Manejo la disponibilidad del chofer al guardar un viaje
        actualizarDisponibilidadChofer(viaje, "save");

        //Actualizo los km del auto al finalizar un viaje
        actualizarKilometrajeAuto(viaje);

        return viajeRepository.save(viaje);
    }

    public void delete(Long id){

        Viaje viaje = findById(id);

        actualizarDisponibilidadChofer(viaje, "delete");

        viajeRepository.deleteById(id);
    }

    public List<SemanaChoferDto> cerrarSemanaChofer(){

        LocalDateTime fechaFin = LocalDateTime.now();
        LocalDateTime fechaInicio = LocalDateTime.now().minusDays(7);

        List<SemanaChoferDto> semanaChoferList = viajeRepository.findSemanaChofer(fechaInicio, fechaFin);

        for(SemanaChoferDto semanaChofer : semanaChoferList){

            float porcentajeViaje = semanaChofer.getChofer().isAutoPropio() ? 0.8f : 0.6f;

            semanaChofer.setSueldoSemanal(semanaChofer.getSueldoSemanal() * porcentajeViaje);

        }

        return semanaChoferList;
    }

    public BalanceMensualDto realizarBalanceMensual(){

        List<BalanceMensualDto> balanceMensualList = viajeRepository.findBalanceMensual(LocalDateTime.now().withDayOfMonth(1), LocalDateTime.now().plusMonths(1).withDayOfMonth(1));

        return procesarDataBalanceMensual(balanceMensualList);

    }

    //En esta funcion lo que hago es utilizar el primer constructor del DTO para procesaro los datos que voy a necesitar en la vista del balance mensual
    //Recibo la lista con los balances mensuales de los choferes, y a partir de eso creo el DTO con la data que se mostrara en la vista.

    private BalanceMensualDto procesarDataBalanceMensual(List<BalanceMensualDto> listaBalanceMensual) {

        Long totalViajes = 0L;
        int contadorChoferes = 0;
        float promedioViajePorChofer; //dividiendo totalViajes / contadorChoferes


        int contadorChoferConAutoPropio = 0;
        int contadorChoferSinAutoPropio = 0;
        float contadorSueldoChoferConAutoPropio = 0;
        float contadorSueldoChoferSinAutoPropio = 0;
        float promedioSueldoChoferConAutoPropio;//dividiendo contadorSueldoChoferConAutoPropio / contadorChoferConAutoPropio
        float promedioSueldoChoferSinAutoPropio;//dividiendo contadorSueldoChoferSinAutoPropio / contadorChoferSinAutoPropio

        float gananciaMensualPorChoferConAutoPropio;
        float gananciaMensualPorChoferSinAutoPropio;

        float gananciaMensualTotal;

        for (BalanceMensualDto dto : listaBalanceMensual) {


            totalViajes += dto.getCantidadViajesChofer();

            contadorChoferes++;

            if(dto.getChofer().isAutoPropio()){

                contadorChoferConAutoPropio++;

                contadorSueldoChoferConAutoPropio += (float) dto.getSueldoMensualChofer();

            }else {

                contadorChoferSinAutoPropio++;

                contadorSueldoChoferSinAutoPropio += (float) dto.getSueldoMensualChofer();

            }

        }

        promedioViajePorChofer = contadorChoferes == 0 ? 0 : (float) totalViajes / contadorChoferes;

        promedioSueldoChoferConAutoPropio = contadorChoferConAutoPropio == 0 ? 0 : (float) ((contadorSueldoChoferConAutoPropio * 0.8) / contadorChoferConAutoPropio); // 80% del sueldo mensual si tiene auto propio
        promedioSueldoChoferSinAutoPropio = contadorChoferSinAutoPropio == 0 ? 0 : (float) ((contadorSueldoChoferSinAutoPropio * 0.6) / contadorChoferSinAutoPropio); // 60% del sueldo mensual si no tiene auto propio

        gananciaMensualPorChoferConAutoPropio = (float) (contadorSueldoChoferConAutoPropio * 0.2);
        gananciaMensualPorChoferSinAutoPropio = (float) (contadorSueldoChoferSinAutoPropio * 0.4);

        gananciaMensualTotal = gananciaMensualPorChoferConAutoPropio + gananciaMensualPorChoferSinAutoPropio;

        return new BalanceMensualDto(totalViajes, gananciaMensualTotal, promedioSueldoChoferSinAutoPropio, promedioSueldoChoferConAutoPropio,
                gananciaMensualPorChoferSinAutoPropio, gananciaMensualPorChoferConAutoPropio, promedioViajePorChofer);
    }

    private void actualizarDisponibilidadChofer(Viaje viaje, String metodo) {
        Chofer chofer = viaje.getChofer();

        switch (metodo) {
            case "save":
                if(viaje.getEstadoViaje() == EstadoViaje.ENCURSO){
                    // Si el viaje está en curso, el chofer no está disponible
                    chofer.setDisponible(false);
                }else{
                    // Si el viaje no está en curso, el chofer está disponible
                    chofer.setDisponible(true);
                }
                break;

            case "delete":
                // Si el viaje se elimina, el chofer vuelve a estar disponible
                if (chofer != null) {
                    chofer.setDisponible(true);
                }
                break;

        }

    }

    private void actualizarKilometrajeAuto(Viaje viaje) {

        if (viaje.getEstadoViaje() == EstadoViaje.FINALIZADO) {

            Chofer chofer = viaje.getChofer();

            if (chofer != null && chofer.getAutoAlquilado() != null) {

                chofer.getAutoAlquilado().setKilometraje(chofer.getAutoAlquilado().getKilometraje() + viaje.getKilometros());

            }
        }
    }

}
