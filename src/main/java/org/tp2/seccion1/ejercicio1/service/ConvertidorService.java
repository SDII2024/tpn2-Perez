package org.tp2.seccion1.ejercicio1.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tp2.seccion1.ejercicio1.convertidor.GetDolarPesoRequest;
import org.tp2.seccion1.ejercicio1.convertidor.GetDolarPesoResponse;
import org.tp2.seccion1.ejercicio1.convertidor.GetPesoDolarRequest;
import org.tp2.seccion1.ejercicio1.convertidor.GetPesoDolarResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConvertidorService {
    static Logger logger = LogManager.getLogger(ConvertidorService.class);

    /**
     * Devuelve importe en U$$ convertido al valor del dólar oficial a la fecha
     * @param importe valor en pesos para convertir a dolar.
     * @return valor en dolar de importe ingresado
     */
    public GetPesoDolarResponse pesoDolar(GetPesoDolarRequest importe){
        GetPesoDolarResponse getPesoDolarResponse = new GetPesoDolarResponse();
        long cotizacion = getCotizacion();
        if (cotizacion != 0) {
            getPesoDolarResponse.setImporteD(importe.getImporteP() / cotizacion);
        }else{
            getPesoDolarResponse.setImporteD(0);
        }
        return getPesoDolarResponse;
    }

    /**
     * Devuelve importe en $ convertido al valor de la cotización a la fecha
     * @param importe valor en dolar para convertir a peso.
     * @return valor en peso de importe ingresado
     */
    public GetDolarPesoResponse dolarPeso(GetDolarPesoRequest importe){
        GetDolarPesoResponse getDolarPesoResponse = new GetDolarPesoResponse();
        getDolarPesoResponse.setImporteP(importe.getImporteD() * getCotizacion());
        return getDolarPesoResponse;
    }

    /**
     * Devuelve la cotización del dólar desde una API externa.
     * Documentacion: dolarapi.com/docs/
     * @return valor de venta del dólar
     */
    private long getCotizacion() {
        ObjectMapper mapper = new ObjectMapper();
        Dolar cotizacion = null;

        try {
            URL url = new URL("https://dolarapi.com/v1/dolares/oficial");
            String responsetoString = getResponsetoString(url);
            cotizacion = mapper.readValue(responsetoString, Dolar.class);

        } catch (IOException e) {
            logger.error("Error al obtener la cotización: {}", e.getMessage());
        }

        return cotizacion != null ? cotizacion.getVenta() : 0;
    }

    private static String getResponsetoString(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}