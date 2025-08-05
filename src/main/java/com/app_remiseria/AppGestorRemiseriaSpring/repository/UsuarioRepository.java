package com.app_remiseria.AppGestorRemiseriaSpring.repository;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    Optional<Usuario> findByUsername(String username);
}
