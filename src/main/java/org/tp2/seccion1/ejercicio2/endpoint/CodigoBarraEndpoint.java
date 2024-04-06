package org.tp2.seccion1.ejercicio2.endpoint;

import org.tp2.seccion1.ejercicio2.codigobarra.GetCodigoBarraRequest;
import org.tp2.seccion1.ejercicio2.codigobarra.GetCodigoBarraResponse;
import org.tp2.seccion1.ejercicio2.service.CodigoBarraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CodigoBarraEndpoint {
    private static final String NAMESPACE = "http://www.seccion1.org/ejercicio2/codigoBarra";

    private CodigoBarraService service;

    @Autowired
    public CodigoBarraEndpoint(CodigoBarraService service) {
        this.service = service;
    }

    @PayloadRoot(localPart = "getCodigoBarraRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetCodigoBarraResponse getCodigoBarraRequest(@RequestPayload GetCodigoBarraRequest request) {
        return service.codigoBarra(request);
    }
}
