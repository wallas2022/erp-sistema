
package erp.sistema;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.CallableStatement;


public class Usuarios {
    private String username;
    private String clave;
    private String nombresCompletos;
    private Date fechaCreacion;
    private Date fechaModificacion;

    Usuarios() {
  }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    public Usuarios(String username, String clave, String nombres_completos) {
        this.username = username;
        this.clave = clave;
        this.nombresCompletos = nombresCompletos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

  

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombres_completos) {
        this.nombresCompletos = nombresCompletos;
    }
    
    // Método para crear un usuario utilizando un procedimiento almacenado
    public void crearUsuario(ConexionBD conexionBD) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL crearUsuario(?, ?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, clave);
                statement.setString(3, nombresCompletos);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
    }
    
    public int autenticarUsuario(String username, String clave, ConexionBD conexionBD) {
        int autenticacionResultado = -1; // Valor predeterminado para autenticación fallida
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL AutenticarUsuario(?, ?, ?)}";
            try (CallableStatement statement = connection.prepareCall(sql)) {
                statement.setString(1, username);
                statement.setString(2, clave);
                statement.registerOutParameter(3, java.sql.Types.INTEGER); // Parámetro de salida
                statement.execute();
                autenticacionResultado = statement.getInt(3); // Obtener el resultado
                System.out.println(autenticacionResultado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return autenticacionResultado;
    }
    
    public int CambioClave(String usurname, String clave){
    
    
        return 0;
    
    
    };

}
