
package org.tp2.seccion2.ejercicio3.paises;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CountryNameResult" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "countryNameResult"
})
@XmlRootElement(name = "CountryNameResponse")
public class CountryNameResponse {

    @XmlElement(name = "CountryNameResult", required = true)
    protected String countryNameResult;

    /**
     * Obtiene el valor de la propiedad countryNameResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryNameResult() {
        return countryNameResult;
    }

    /**
     * Define el valor de la propiedad countryNameResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryNameResult(String value) {
        this.countryNameResult = value;
    }

}
