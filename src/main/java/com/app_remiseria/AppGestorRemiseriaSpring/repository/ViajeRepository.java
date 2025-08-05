package com.app_remiseria.AppGestorRemiseriaSpring.repository;

import com.app_remiseria.AppGestorRemiseriaSpring.dto.BalanceMensualDto;
import com.app_remiseria.AppGestorRemiseriaSpring.dto.SemanaChoferDto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Long> {

    @Query("SELECT new com.app_remiseria.AppGestorRemiseriaSpring.dto.SemanaChoferDto(v.chofer, COUNT(v), SUM(v.kilometros), SUM(v.kilometros * v.valorKm)) " +
            "FROM Viaje v " +
            "WHERE v.estadoViaje = 'FINALIZADO' AND v.fecha BETWEEN :fechaInicio AND :fechaFin AND v.chofer.eliminado = false " +
            "GROUP BY v.chofer")
    List<SemanaChoferDto> findSemanaChofer(@Param("fechaInicio") LocalDateTime fechaInicio,@Param("fechaFin") LocalDateTime fechaFin);

    @Query("SELECT new com.app_remiseria.AppGestorRemiseriaSpring.dto.BalanceMensualDto(v.chofer, COUNT(v), SUM(v.kilometros * v.valorKm)) " +
            "FROM Viaje v " +
            "WHERE v.estadoViaje = 'FINALIZADO' AND v.fecha BETWEEN :fechaInicio AND :fechaFin " +
            "GROUP BY v.chofer")
    List<BalanceMensualDto> findBalanceMensual(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);

}
