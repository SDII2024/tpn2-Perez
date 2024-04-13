//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.04.11 a las 04:10:51 PM ART 
//


package org.tp2.seccion1.ejercicio1.convertidor;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tp2.seccion1.ejercicio1.convertidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tp2.seccion1.ejercicio1.convertidor
     * 
     */
    public ObjectFactory() {
        //empty method
    }

    /**
     * Create an instance of {@link GetPesoDolarRequest }
     * 
     */
    public GetPesoDolarRequest createGetPesoDolarRequest() {
        return new GetPesoDolarRequest();
    }

    /**
     * Create an instance of {@link GetDolarPesoRequest }
     * 
     */
    public GetDolarPesoRequest createGetDolarPesoRequest() {
        return new GetDolarPesoRequest();
    }

    /**
     * Create an instance of {@link GetPesoDolarResponse }
     * 
     */
    public GetPesoDolarResponse createGetPesoDolarResponse() {
        return new GetPesoDolarResponse();
    }

    /**
     * Create an instance of {@link GetDolarPesoResponse }
     * 
     */
    public GetDolarPesoResponse createGetDolarPesoResponse() {
        return new GetDolarPesoResponse();
    }

}
