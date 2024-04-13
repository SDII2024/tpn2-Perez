package org.tp2.seccion2.ejercicio3;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio3.cliente.UIPaises;

/**
 * Ejercicio 3 -  Consume servicio web "Paises"
 */
@Component
public class ConsumoWSPaises {
    static Logger logger = LogManager.getLogger(ConsumoWSPaises.class);

    private final UIPaises uiPaises;

    @Autowired
    public ConsumoWSPaises(UIPaises uiPaises) {
        this.uiPaises = uiPaises;
    }

    @PostConstruct
    public void iniciarInterfaz() {
        uiPaises.crearVentana();
    }

}

