package org.tp2.seccion2.ejercicio1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tp2.seccion2.ejercicio1.cliente.ClienteCalculadora;

@Configuration
public class ConfiguracionCalculadora {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("org.tp2.seccion2.ejercicio1.calculadora");
        return marshaller;
    }

    @Bean
    public ClienteCalculadora clienteCalculadora(Jaxb2Marshaller marshaller) {
        ClienteCalculadora client = new ClienteCalculadora();
        client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
