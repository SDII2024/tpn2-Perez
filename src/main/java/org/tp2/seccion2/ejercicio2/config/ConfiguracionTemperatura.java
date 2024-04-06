package org.tp2.seccion2.ejercicio2.config;

import org.tp2.seccion2.ejercicio2.cliente.ClienteTemperatura;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConfiguracionTemperatura{

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("org.tp2.seccion2.ejercicio2.temperatura");
        return marshaller;
    }

    @Bean
    public ClienteTemperatura clienteTemperatura(Jaxb2Marshaller marshaller) {
        ClienteTemperatura client = new ClienteTemperatura();
        client.setDefaultUri("https://www.w3schools.com/xml/tempconvert.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
