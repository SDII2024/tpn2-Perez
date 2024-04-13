package org.tp2.seccion2.ejercicio2;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio2.cliente.UiTemperatura;

/**
 * Ejercicio 2 -  Consume servicio web "Temperatura"
 */
@Component
public class ConsumoWSTemperatura {
    private final UiTemperatura uiTemperatura;

    @Autowired
    public ConsumoWSTemperatura(UiTemperatura uiTemperatura) {
        this.uiTemperatura = uiTemperatura;
    }

    @PostConstruct
    public void iniciarInterfaz() {
        uiTemperatura.crearVentana();
    }
}

