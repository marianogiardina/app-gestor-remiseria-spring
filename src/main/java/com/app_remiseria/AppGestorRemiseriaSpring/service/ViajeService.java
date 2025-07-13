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
        Chofer chofer = viaje.getChofer();

        if(viaje.getEstadoViaje() == EstadoViaje.ENCURSO){
            // Si el viaje está en curso, el chofer no está disponible
            chofer.setDisponible(false);
        }else{
            // Si el viaje no está en curso, el chofer está disponible
            chofer.setDisponible(true);
        }

        return viajeRepository.save(viaje);
    }

    public void delete(Long id){
        viajeRepository.deleteById(id);
    }

}
