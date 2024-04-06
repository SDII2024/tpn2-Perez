
package org.tp2.seccion2.ejercicio2.temperatura;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "TempConvertSoap", targetNamespace = "https://www.w3schools.com/xml/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TempConvertSoap {


    /**
     * 
     * @param fahrenheit
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "FahrenheitToCelsius", action = "https://www.w3schools.com/xml/FahrenheitToCelsius")
    @WebResult(name = "FahrenheitToCelsiusResult", targetNamespace = "https://www.w3schools.com/xml/")
    @RequestWrapper(localName = "FahrenheitToCelsius", targetNamespace = "https://www.w3schools.com/xml/", className = "org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsius")
    @ResponseWrapper(localName = "FahrenheitToCelsiusResponse", targetNamespace = "https://www.w3schools.com/xml/", className = "org.tp2.seccion2.ejercicio2.temperatura.FahrenheitToCelsiusResponse")
    public String fahrenheitToCelsius(
        @WebParam(name = "Fahrenheit", targetNamespace = "https://www.w3schools.com/xml/")
        String fahrenheit);

    /**
     * 
     * @param celsius
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CelsiusToFahrenheit", action = "https://www.w3schools.com/xml/CelsiusToFahrenheit")
    @WebResult(name = "CelsiusToFahrenheitResult", targetNamespace = "https://www.w3schools.com/xml/")
    @RequestWrapper(localName = "CelsiusToFahrenheit", targetNamespace = "https://www.w3schools.com/xml/", className = "org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheit")
    @ResponseWrapper(localName = "CelsiusToFahrenheitResponse", targetNamespace = "https://www.w3schools.com/xml/", className = "org.tp2.seccion2.ejercicio2.temperatura.CelsiusToFahrenheitResponse")
    public String celsiusToFahrenheit(
        @WebParam(name = "Celsius", targetNamespace = "https://www.w3schools.com/xml/")
        String celsius);

}
