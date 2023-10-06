package erp.sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private final String url = "jdbc:mysql://localhost:3306/bd_erp";
    private final String usuarioDB = "root";
    private final String claveDB = "meli$1116";
    private Connection connection;

    public Connection conectar() {
        try {
            connection = DriverManager.getConnection(url, usuarioDB, claveDB);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void desconectar() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}

