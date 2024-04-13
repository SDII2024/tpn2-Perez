package org.tp2.seccion1.ejercicio1.endpoint;

import org.tp2.seccion1.ejercicio1.convertidor.GetDolarPesoRequest;
import org.tp2.seccion1.ejercicio1.convertidor.GetDolarPesoResponse;
import org.tp2.seccion1.ejercicio1.convertidor.GetPesoDolarRequest;
import org.tp2.seccion1.ejercicio1.convertidor.GetPesoDolarResponse;
import org.tp2.seccion1.ejercicio1.service.ConvertidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ConvertidorEndpoint {
    private static final String NAMESPACE = "http://www.tp2.org/seccion1/ejercicio1/convertidor";

    private final ConvertidorService service;

    @Autowired
    public ConvertidorEndpoint(ConvertidorService service) {
        this.service = service;
    }

    @PayloadRoot(localPart = "getPesoDolarRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetPesoDolarResponse getPesoDolarRequest(@RequestPayload GetPesoDolarRequest request) {
        return service.pesoDolar(request);
    }

    @PayloadRoot(localPart = "getDolarPesoRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetDolarPesoResponse getDolarPesoRequest(@RequestPayload GetDolarPesoRequest request) {
        return service.dolarPeso(request);
    }
}
