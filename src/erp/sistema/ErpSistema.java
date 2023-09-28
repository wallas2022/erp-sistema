
package erp.sistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Walter Rosales
 */
public class ErpSistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
                // Establecer la conexión a la base de datos (reemplaza con tu URL, usuario y contraseña)
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_erp", "root", "meli$1116");

              
               // Ejecutar una consulta SELECT (reemplaza con tu consulta SQL)
            String consulta = "SELECT * FROM usuarios";
            statement =  (Statement) conexion.createStatement();
            resultSet = statement.executeQuery(consulta);

            // Procesar los resultados de la consulta
            while (resultSet.next()) {
                // Obtener datos de las columnas
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int clave = resultSet.getInt("clave");

                // Haz algo con los datos, por ejemplo, imprimirlos
                System.out.println("ID: " + id + ", Username: " + username + ", Clave: " + clave);
            }
              conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("no se puedo conectar a la base de datos");
            }
        
        login inicio = new login();
        
        inicio.setVisible(true);
        
    }
    
}
