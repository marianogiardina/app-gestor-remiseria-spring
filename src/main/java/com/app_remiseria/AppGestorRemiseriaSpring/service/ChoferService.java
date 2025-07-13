package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoferService {

    @Autowired
    ChoferRepository choferRepository;

    @Autowired
    AutoService autoService;

    public List<Chofer> findAll(){

        return choferRepository.findAll();

    }

    public Chofer findById(Long id){
        return choferRepository.findById(id).orElse(null);
    }

    public Chofer save(Chofer chofer){

        //Manejo el estado del auto antes de guardar el chofer.

        actualizarDisponibilidadDeAuto(chofer, "save");


        return choferRepository.save(chofer);
    }

    public void delete(Long id){

        // Antes de eliminar el chofer, verifico si tiene un auto alquilado y lo marco como disponible.
        Chofer chofer = findById(id);

        actualizarDisponibilidadDeAuto(chofer, "delete");

        choferRepository.deleteById(id);
    }

    //TODO: Si el chofer pasa de tener auto alquilado a tener auto propio, no se actualiza la disponibilidad del auto alquilado.

    private void actualizarDisponibilidadDeAuto(Chofer chofer, String metodo) {

        Auto auto = chofer.getAutoAlquilado();

        switch (metodo) {
            case "save":
                if (auto != null && !chofer.isAutoPropio()) {
                    auto.setDisponible(false);
                    autoService.save(auto);
                }
                break;

            case "delete":
                if (auto != null && !chofer.isAutoPropio()) {
                    auto.setDisponible(true);
                    autoService.save(auto);
                }
                break;

            default:
                break;
        }

    }

}
