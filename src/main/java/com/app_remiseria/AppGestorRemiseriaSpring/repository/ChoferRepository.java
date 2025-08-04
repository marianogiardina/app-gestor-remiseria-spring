package com.app_remiseria.AppGestorRemiseriaSpring.repository;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoferRepository extends JpaRepository<Chofer,Long> {

}
