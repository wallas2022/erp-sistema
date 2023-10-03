
package erp.sistema;



import com.mysql.cj.xdevapi.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Walter Rosales
 */
public class ErpSistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
        
        ConexionBD conn = new ConexionBD() ;
        
        
     // Ejemplo de consulta SELECT
            ResultSet resultado = conn.ejecutarConsulta("SELECT * FROM usuarios");
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String username = resultado.getString("username");

                
                System.out.println("ID: " + id + ", Username: " + username);
    
            }
            
             // Ejemplo de consulta UPDATE
            int filasActualizadas = conn.ejecutarActualizacion("UPDATE usuarios SET clave = ? WHERE id = ?", "123456", 1);
            System.out.println("Se actualizo correctamente: "+ filasActualizadas);
            // Cerrar la conexión cuando hayas terminado
            conn.cerrarConexion();
        
        login inicio = new login();
            
        inicio.setVisible(true);
        
    }
    
}
