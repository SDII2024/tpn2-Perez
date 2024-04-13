package org.tp2.seccion2.ejercicio3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.tp2.seccion2.ejercicio3.cliente.ClientePaises;

@Configuration
public class ConfiguracionPaises {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("org.tp2.seccion2.ejercicio3.paises");
        return marshaller;
    }

    @Bean
    public ClientePaises clientePaises(Jaxb2Marshaller marshaller) {
        ClientePaises client = new ClientePaises();
        client.setDefaultUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
