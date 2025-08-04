package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.model.EstadoViaje;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Viaje;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ChoferRepository;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
