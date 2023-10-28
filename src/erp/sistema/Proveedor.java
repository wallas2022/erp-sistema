
package erp.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Proveedor {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String nit;
    private int estado;
    
    public Proveedor(){}
     
   

    public Proveedor(int id, String nombre, String direccion, String telefono, String correo, String nit, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.nit = nit;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
      // Método para crear un proveedor utilizando un procedimiento almacenado
    public Proveedor crearProveedor(ConexionBD conexionBD, Proveedor proveedor) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL CrearProveedor(?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, proveedor.nombre);
                statement.setString(2, proveedor.direccion);
                statement.setString(3, proveedor.telefono);
                statement.setString(4, proveedor.correo);
                statement.setString(5, proveedor.nit);
                statement.setInt(6, proveedor.estado);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return proveedor;
    }
    
    // listar proveedores
    public ArrayList<Proveedor> listarProveedor(ConexionBD conexionBD){

  
    ArrayList<Proveedor> listaproveedores = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM proveedores";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Proveedor  proveedor = new Proveedor();
                   proveedor.setId(resultSet.getInt("id"));
                    proveedor.setNit(resultSet.getString("nit"));
                   proveedor.setNombre(resultSet.getString("nombre"));
                   proveedor.setDireccion(resultSet.getString("direccion"));
                   proveedor.setTelefono(resultSet.getString("telefono"));
                   proveedor.setCorreo(resultSet.getString("correo"));
                   proveedor.setEstado( resultSet.getInt( "estado"));

                    
                    listaproveedores.add(proveedor);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
     System.out.println(listaproveedores);
    return listaproveedores;
    };
    
     // Modificar proveedores
    public int modificarProveedores(Proveedor proveedor) throws SQLException{
         String UPDATE_PROVEEDOR_SQL = "UPDATE Proveedores SET nombre = ?, direccion = ?, telefono = ?, correo=?, estado = ?, nit = ? WHERE id = ?";
           ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROVEEDOR_SQL);
            preparedStatement.setInt(7, proveedor.id);
            preparedStatement.setString(1, proveedor.nombre);
            preparedStatement.setString(2, proveedor.direccion);
            preparedStatement.setString(3, proveedor.telefono);
            preparedStatement.setString(4, proveedor.correo);
            preparedStatement.setInt(5, proveedor.estado);
            preparedStatement.setString(6, proveedor.nit);
            
            System.out.println(proveedor.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
   
    public Proveedor items(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        return this;
    }
       
@Override
public String toString() {
    return nombre;
}

 // listar proveedor
    public int mostrarProveedor(ConexionBD conexionBD, String nombre) {

         String sql = "SELECT * FROM proveedores WHERE nombre = ?";
        try (PreparedStatement stmt = conexionBD.conectar().prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rs.getInt("id"));
                    proveedor.setNombre(rs.getString("nombre"));
                    // Set other attributes as well
                    return proveedor.getId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O manejar la excepción de manera más adecuada
        }
        return 0; // Devuelve null si no encuentra el almacén
  
    }
    
}
