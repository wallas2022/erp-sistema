
package erp.sistema;
import java.awt.List;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuarios {
    private int id;
    private String username;
    private String clave;
    private String nombres_completos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombresCompletos() {
        return nombres_completos;
    }

    public void setNombresCompletos(String nombres_completos) {
        this.nombres_completos = nombres_completos;
    }
    private Date fechaCreacion;
    private Date fechaModificacion;


    Usuarios(){};
    
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
    
    public Usuarios(int id,String username, String clave, String nombres_completos) {
        this.id = id;
        this.username = username;
        this.clave = clave;
        this.nombres_completos = nombres_completos ;
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

 
    
    // Método para crear un usuario utilizando un procedimiento almacenado
    public Usuarios crearUsuario(ConexionBD conexionBD, Usuarios usuario) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL crearUsuario(?, ?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, usuario.username);
                statement.setString(2, usuario.nombres_completos);
                statement.setString(3, usuario.clave);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return usuario;
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
    
    public ArrayList<Usuarios> listarUsuarios(ConexionBD conexionBD){
    // Usuarios usuario = new Usuarios();
  
    ArrayList<Usuarios> listausuarios = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM usuarios";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Usuarios  user = new Usuarios();
                   user.setId(resultSet.getInt("id"));
                   user.setUsername(resultSet.getString("username"));
                   user.setNombresCompletos(resultSet.getString("nombres_completos"));

                    
                    listausuarios.add(user);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
    return listausuarios;
    };
    // Modificar usuario
    public int modificarUsuario(Usuarios usuario) throws SQLException{
         String UPDATE_USUARIO_SQL = "UPDATE Usuarios SET username = ?, nombres_completos = ? WHERE id = ?";
            ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL);
            preparedStatement.setString(1, usuario.username);
            preparedStatement.setString(2, usuario.nombres_completos);
            preparedStatement.setInt(3, usuario.id);
            System.out.println(usuario.getNombresCompletos());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
    public int CambioClave(String usurname, String clave){
    
    
    return 0;
    
    
    };

}
