package org.tp2.seccion1.ejemplo.endpoint;

import org.tp2.seccion1.ejemplo.service.BirthdayService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.tp2.seccion1.ejemplo.birthday.GetAgeResponse;
import org.tp2.seccion1.ejemplo.birthday.GetBirthdayRequest;

@Endpoint
public class BirthdayEndpoint {
    private static final String NAMESPACE = "http://www.seccion1.org/ejemplo/birthday";

    private BirthdayService service;

    @Autowired
    public BirthdayEndpoint(BirthdayService service) {
        this.service = service;
    }

    @PayloadRoot(localPart = "getBirthdayRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetAgeResponse getBirthdayRequest(@RequestPayload GetBirthdayRequest request) {
        return service.age(request);
    }
}
