
package erp.sistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConeccionMysql {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_erp";
    private static final String USUARIO = "root";
    private static final String CLAVE = "meli$1116";

    public static Connection obtenerConexion() throws SQLException {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO,CLAVE);
             String nombreBaseDeDatos = conexion.getCatalog();
            
                System.out.println("Se conecto correctamante a base de datos: " + nombreBaseDeDatos);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conexion;
    }
    
}
