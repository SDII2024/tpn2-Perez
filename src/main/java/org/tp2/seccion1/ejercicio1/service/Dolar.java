package org.tp2.seccion1.ejercicio1.service;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dolar {
    @JsonProperty("moneda")
    String moneda;
    @JsonProperty("casa")
    String casa;
    @JsonProperty("nombre")
    String nombre;
    @JsonProperty("compra")
    long compra;
    @JsonProperty("venta")
    long venta;
    @JsonProperty("fechaActualizacion")
    String fechaActualizacion;
}
