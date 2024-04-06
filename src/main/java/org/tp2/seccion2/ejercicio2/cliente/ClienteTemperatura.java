package org.tp2.seccion2.ejercicio2.cliente;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheit;
import org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheitResponse;
import org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsius;
import org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsiusResponse;

public class ClienteTemperatura extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ClienteTemperatura.class);

    public CelsiusToFahrenheitResponse getCelsiusToFahrenheit(String temperatura) {

        CelsiusToFahrenheit request = new CelsiusToFahrenheit();
        request.setCelsius(temperatura);

        log.info("Convirtiendo temperatura");

        String url = "https://www.w3schools.com/xml/tempconvert.asmx";
        String soapAction = "https://www.w3schools.com/xml/CelsiusToFahrenheit";

        return (CelsiusToFahrenheitResponse) getWebServiceTemplate()
                .marshalSendAndReceive(url, request,
                        new SoapActionCallback(soapAction));
    }

    public FahrenheitToCelsiusResponse getFahrenheitToCelsius(String temperatura) {

        FahrenheitToCelsius request = new FahrenheitToCelsius();
        request.setFahrenheit(temperatura);

        log.info("Convirtiendo temperatura");

        String url = "https://www.w3schools.com/xml/tempconvert.asmx";
        String soapAction = "https://www.w3schools.com/xml/FahrenheitToCelsius";

        return (FahrenheitToCelsiusResponse) getWebServiceTemplate()
                .marshalSendAndReceive(url, request,
                        new SoapActionCallback(soapAction));
    }

}