package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.AutoRepository;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.ChoferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: QUE SEA SOFT DELETE Y QE SI SE ELIMINA EL AUTO Y LO TIENE ALGUN CHOFER SE LE DESASIGNE, PERO ME FIJO SI LLEGO CON EL TIEMPO

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private ChoferRepository choferRepository;

    public List<Auto> findAll(){

        return autoRepository.findAll();

    }

    public Auto findById(Long id){
        return autoRepository.findById(id).orElse(null);
    }

    public Auto save(Auto auto){

        return autoRepository.save(auto);
    }

    public void delete(Long id){
        autoRepository.deleteById(id);
    }

}
