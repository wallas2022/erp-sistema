package erp.sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {
    private Connection conexion;
    private static final String url = "jdbc:mysql://localhost:3306/bd_erp";
    private static final String usuario = "root";
    private static final String clave = "meli$1116";

    // Constructor: Establece la conexión a la base de datos
    public ConexionBD() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexion a la base de datos exitosa");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC: " + e.getMessage());
        }
    }

    // Método para ejecutar una consulta SELECT
    public ResultSet ejecutarConsulta(String consulta, Object... parametros) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer parámetros (si los hay)
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }

            return statement.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Método para ejecutar una consulta INSERT, UPDATE o DELETE
    public int ejecutarActualizacion(String consulta, Object... parametros) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Establecer parámetros (si los hay)
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al ejecutar la actualización: " + e.getMessage());
        }
    }

    // Método para cerrar la conexión a la base de datos
    public void cerrarConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    PreparedStatement prepareStatement(String consulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

