package org.tp2.seccion2.ejercicio2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tp2.seccion2.ejercicio2.cliente.ClienteTemperatura;
import org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheitResponse;
import org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsiusResponse;

/**
 * Ejercicio 2 -  Consume servicio web "Temperatura"
 */
@Component
public class ConsumoWSTemperatura {
    static Logger logger = LogManager.getLogger(ConsumoWSTemperatura.class);

    @Bean
    public CommandLineRunner lookup(ClienteTemperatura clienteTemperatura) {
        return args -> {
            String temperatura = "5";
            CelsiusToFahrenheitResponse response1 = clienteTemperatura.getCelsiusToFahrenheit(temperatura);
            String ejCelsiusToFahrenheit = temperatura + " grados Celcius equivalen a " + response1.getCelsiusToFahrenheitResult() + " Fahrenheit";
            logger.info(ejCelsiusToFahrenheit);

            temperatura = "50";
            FahrenheitToCelsiusResponse response2 = clienteTemperatura.getFahrenheitToCelsius(temperatura);
            String ejFahrenheitToCelsius = temperatura + " Fahrenheit equivalen a " + response2.getFahrenheitToCelsiusResult() + " grados Celcius";
            logger.info(ejFahrenheitToCelsius);
        };
    }
}

