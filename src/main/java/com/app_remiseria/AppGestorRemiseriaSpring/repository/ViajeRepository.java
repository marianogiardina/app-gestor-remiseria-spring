package com.app_remiseria.AppGestorRemiseriaSpring.repository;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Long> {



}
