package org.tp2.seccion2.ejercicio1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio1.cliente.UiCalculadora;

/**
 * Ejercicio 1 -  Consume servicio web "Calculadora"
 */
@Component
public class ConsumoWSCalculadora {
    private final UiCalculadora uiCalculadora;

    @Autowired
    public ConsumoWSCalculadora(UiCalculadora uiCalculadora) {
        this.uiCalculadora = uiCalculadora;
    }

    @PostConstruct
    public void iniciarInterfaz() {
        uiCalculadora.crearVentana();
    }

}

