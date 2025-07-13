package com.app_remiseria.AppGestorRemiseriaSpring.service;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepository;

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
