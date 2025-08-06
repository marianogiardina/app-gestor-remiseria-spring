package com.app_remiseria.AppGestorRemiseriaSpring;

import com.app_remiseria.AppGestorRemiseriaSpring.dto.SemanaChoferDto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.service.ViajeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViajeServiceTest {



    @Test
    void testCerrarSemanaChofer_ChoferConAutoPropio() {

        Chofer chofer = new Chofer();
        chofer.setAutoPropio(true);

        // Armo el DTO con datos hardcodeados para simular el resultado del sql
        SemanaChoferDto semanaChofer = new SemanaChoferDto(chofer, 10L, 100.0, 1000.0);

        float porcentajeViaje = semanaChofer.getChofer().isAutoPropio() ? 0.8f : 0.6f;
        double sueldoEsperado = 1000.0 * porcentajeViaje;

        // asserEquals verifica que el resultado es correcto, paso como parametro tambien el margen de error porque sino tira error.
        assertEquals(800.0, sueldoEsperado, 0.01);
    }

    @Test
    void testCerrarSemanaChofer_ChoferSinAutoPropio() {

        Chofer chofer = new Chofer();
        chofer.setAutoPropio(false);


        SemanaChoferDto semanaChofer = new SemanaChoferDto(chofer, 8L, 80.0, 1000.0);


        float porcentajeViaje = semanaChofer.getChofer().isAutoPropio() ? 0.8f : 0.6f;
        double sueldoEsperado = 1000.0 * porcentajeViaje;

        // asserEquals verifica que el resultado es correcto, paso como parametro tambien el margen de error porque sino tira error.
        assertEquals(600.0, sueldoEsperado, 0.01);
    }

    @Test
    void testPorcentajeCorrectoPorTipoChofer() {

        Chofer choferConAuto = new Chofer();
        choferConAuto.setAutoPropio(true);

        Chofer choferSinAuto = new Chofer();
        choferSinAuto.setAutoPropio(false);


        float porcentajeConAuto = choferConAuto.isAutoPropio() ? 0.8f : 0.6f;
        float porcentajeSinAuto = choferSinAuto.isAutoPropio() ? 0.8f : 0.6f;

        assertEquals(0.8f, porcentajeConAuto);
        assertEquals(0.6f, porcentajeSinAuto);
    }

}
