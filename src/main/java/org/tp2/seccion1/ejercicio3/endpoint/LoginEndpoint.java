package org.tp2.seccion1.ejercicio3.endpoint;

import org.tp2.seccion1.ejercicio3.login.GetLoginRequest;
import org.tp2.seccion1.ejercicio3.login.GetLoginResponse;
import org.tp2.seccion1.ejercicio3.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LoginEndpoint {
    private static final String NAMESPACE = "http://www.seccion1.org/ejercicio3/login";

    private LoginService service;

    @Autowired
    public LoginEndpoint(LoginService service) {
        this.service = service;
    }

    @PayloadRoot(localPart = "getLoginRequest", namespace = NAMESPACE)
    @ResponsePayload
    public GetLoginResponse getLoginRequest(@RequestPayload GetLoginRequest request) {
        return service.login(request);
    }
}
