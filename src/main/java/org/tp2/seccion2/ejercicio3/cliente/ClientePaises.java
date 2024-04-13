package org.tp2.seccion2.ejercicio3.cliente;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.tp2.seccion2.ejercicio3.paises.*;

public class ClientePaises extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ClientePaises.class);
    private static final String URL = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

    public ListOfCountryNamesByNameResponse getListOfCountryNamesByName() {

        ListOfCountryNamesByName request = new ListOfCountryNamesByName();

        log.info("Buscando lista de paises por nombre");

        String soapAction = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/ListOfCountryNamesByName";

        return (ListOfCountryNamesByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public CapitalCityResponse getCapitalCity(String codigoPais) {

        CapitalCity request = new CapitalCity();
        request.setSCountryISOCode(codigoPais);

        log.info("Buscando capital");

        String soapAction = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CapitalCity";

        return (CapitalCityResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }
    public CountryCurrencyResponse getCountryCurrency(String codigoPais) {

        CountryCurrency request = new CountryCurrency();
        request.setSCountryISOCode(codigoPais);

        log.info("Buscando moneda de pais");

        String soapAction = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CountryCurrency";

        return (CountryCurrencyResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public CountryFlagResponse getCountryFlag(String codigoPais) {

        CountryFlag request = new CountryFlag();
        request.setSCountryISOCode(codigoPais);

        log.info("Buscando bandera");

        String soapAction = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CountryFlag";

        return (CountryFlagResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

    public CountryIntPhoneCodeResponse getCountryIntPhoneCode(String codigoPais) {

        CountryIntPhoneCode request = new CountryIntPhoneCode();
        request.setSCountryISOCode(codigoPais);

        log.info("Buscando codigo de telefono");

        String soapAction = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CountryIntPhoneCode";

        return (CountryIntPhoneCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(URL, request,
                        new SoapActionCallback(soapAction));
    }

}