package org.tp2.seccion2.ejercicio1.cliente;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.tp2.seccion2.ejercicio1.calculadora.*;

public class ClienteCalculadora extends WebServiceGatewaySupport {
    private static final String URL = "http://www.dneonline.com/calculator.asmx";

    private static final Logger log = LoggerFactory.getLogger(ClienteCalculadora.class);

    public AddResponse sumar(int a, int b) {
        Add request = new Add();
        request.setIntA(a);
        request.setIntB(b);

        log.info("Invocando servicio de suma");

        String soapAction = "http://tempuri.org/Add";

        return (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public SubtractResponse restar(int a, int b) {
        Subtract request = new Subtract();
        request.setIntA(a);
        request.setIntB(b);

        log.info("Invocando servicio de resta");

        String soapAction = "http://tempuri.org/Subtract";

        return (SubtractResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public MultiplyResponse multiplicar(int a, int b) {
        Multiply request = new Multiply();
        request.setIntA(a);
        request.setIntB(b);

        log.info("Invocando servicio de multiplicacion");

        String soapAction = "http://tempuri.org/Multiply";

        return (MultiplyResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public DivideResponse dividir(int a, int b) {
        Divide request = new Divide();
        request.setIntA(a);
        request.setIntB(b);

        log.info("Invocando servicio de division");

        String soapAction = "http://tempuri.org/Divide";

        return (DivideResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }




}