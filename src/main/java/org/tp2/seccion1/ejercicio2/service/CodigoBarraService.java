package org.tp2.seccion1.ejercicio2.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tp2.seccion1.ejercicio2.codigobarra.GetCodigoBarraRequest;
import org.tp2.seccion1.ejercicio2.codigobarra.GetCodigoBarraResponse;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class CodigoBarraService {
    static Logger logger = LogManager.getLogger(CodigoBarraService.class);

    private static final String URL = "jdbc:sqlite:productos.db";

    /**
     * Devuelve el nombre y el precio del producto a partir de su código
     * @param codigo codigo del producto a consultar
     * @return String con el nombre y precio del producto
     */
    public GetCodigoBarraResponse codigoBarra(GetCodigoBarraRequest codigo){
        GetCodigoBarraResponse getCodigoBarraResponse = new GetCodigoBarraResponse();
        getCodigoBarraResponse.setResultadoConsulta("No se encontró ningún producto");

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT nombre, precio FROM productos WHERE codigo = ?")) {

            pstmt.setInt(1, codigo.getCodigo());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                getCodigoBarraResponse.setResultadoConsulta("El producto " + nombre + " tiene un precio de " + precio);
            }
        } catch (SQLException e) {
            logger.error("Error al obtener nombre y precio por código", e);
        }

        return getCodigoBarraResponse;
    }

    /**
     * Main y siguientes métodos se utilizan para inicializar la base de datos y
     * preparar algunos datos de ejemplo
     */
    public static void main(String[] args){
        CodigoBarraService codigoBarra = new CodigoBarraService();
        codigoBarra.crearTablaBD();
        codigoBarra.insertarProducto(123456789, "producto1", 10);
        codigoBarra.insertarProducto(234567891, "producto2", 12);
        codigoBarra.insertarProducto(345678912, "producto3", 18);
    }

    public void crearTablaBD(){
        String creacionTablaSQL = "CREATE TABLE IF NOT EXISTS productos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "codigo INTEGER NOT NULL,"
                + "nombre TEXT NOT NULL,"
                + "precio NUMERIC NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(creacionTablaSQL);
            logger.info("Tabla productos creada exitosamente");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void insertarProducto(int codigo, String nombre, int precio) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO productos (codigo, nombre, precio) VALUES (?, ?, ?)")) {

            pstmt.setInt(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, precio);
            pstmt.executeUpdate();
            logger.info("Usuario insertado correctamente");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }
}