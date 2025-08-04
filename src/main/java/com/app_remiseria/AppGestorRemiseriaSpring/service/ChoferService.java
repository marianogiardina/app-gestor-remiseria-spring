package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoferService {

    @Autowired
    ChoferRepository choferRepository;

    @Autowired
    AutoService autoService;

    public List<Chofer> findAll(){

        List<Chofer> choferesActivos = new ArrayList<>();

        for (Chofer chofer : choferRepository.findAll()) {

            if(!chofer.isEliminado()){

                choferesActivos.add(chofer);

            }

        }

        return choferesActivos;

    }

    public Chofer findById(Long id){
        return choferRepository.findById(id).orElse(null);
    }

    public Chofer save(Chofer chofer){


        actualizarDisponibilidadDeAuto(chofer, "save");

        return choferRepository.save(chofer);
    }

    public void delete(Long id){

        // Antes de eliminar el chofer, verifico si tiene un auto alquilado y lo marco como disponible.
        Chofer chofer = findById(id);

        if (chofer != null) {
            // Marcar como eliminado en lugar de eliminar físicamente
            chofer.setEliminado(true);
            chofer.setDisponible(false);

            actualizarDisponibilidadDeAuto(chofer, "delete");

            choferRepository.save(chofer);
        }
    }

    private void actualizarDisponibilidadDeAuto(Chofer chofer, String metodo) {

        Auto auto = chofer.getAutoAlquilado();

        switch (metodo) {
            case "save":

                if(chofer.isAutoPropio() && chofer.getId() != null) {

                    Chofer choferSinActualizar = findById(chofer.getId());

                    Auto autoSinActualizar = choferSinActualizar.getAutoAlquilado();

                    if(autoSinActualizar != null) {
                        // Si el chofer tiene auto propio, el auto alquilado se marca como disponible
                        autoSinActualizar.setDisponible(true);
                        autoService.save(autoSinActualizar);
                    }
                }

                if (auto != null && !chofer.isAutoPropio()) {

                    auto.setDisponible(false);
                    autoService.save(auto);
                }
                break;

            case "delete":
                if (auto != null && !chofer.isAutoPropio()) {
                    chofer.setAutoAlquilado(null);
                    auto.setDisponible(true);
                    autoService.save(auto);
                }
                break;

            default:
                break;
        }

    }

}
