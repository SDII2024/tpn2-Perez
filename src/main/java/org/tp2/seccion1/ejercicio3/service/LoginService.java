package org.tp2.seccion1.ejercicio3.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tp2.seccion1.ejercicio3.login.GetLoginRequest;
import org.tp2.seccion1.ejercicio3.login.GetLoginResponse;
import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class LoginService {
    static Logger logger = LogManager.getLogger(LoginService.class);

    private static final String URL = "jdbc:sqlite:login.db";

    /**
     * Valida usuario y contraseña en la base
     * @param usuario nombre y contraseña de usuario a validar
     * @return true si es valido, false si no lo es.
     */
    public GetLoginResponse login(GetLoginRequest usuario){
        GetLoginResponse getLoginResponse = new GetLoginResponse();
        getLoginResponse.setValidado(false);

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?")) {
            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getClave());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                getLoginResponse.setValidado(true);
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return getLoginResponse;
    }

    /**
     * Main y siguientes métodos se utilizan para inicializar la base de datos y
     * preparar algunos datos de ejemplo
     */
    public static void main(String[] args){
        LoginService login = new LoginService();
        login.crearTablaBD();
        login.insertarUsuario("usuario1", "contrasena1");
        login.insertarUsuario("usuario2", "contrasena2");
        login.insertarUsuario("usuario3", "contrasena3");
    }

    public void crearTablaBD(){
        String creacionTablaSQL = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "contrasena TEXT NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(creacionTablaSQL);
            logger.info("Tabla usuarios creada exitosamente");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public void insertarUsuario(String nombre, String contrasena) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO usuarios (nombre, contrasena) VALUES (?, ?)")) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena);
            pstmt.executeUpdate();
            logger.info("Usuario insertado correctamente");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }
}