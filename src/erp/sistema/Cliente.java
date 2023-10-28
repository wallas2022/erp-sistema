
package erp.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Cliente {
    
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String nit;
    private int estado;
    
    public Cliente(){}

    public Cliente(int id, String nombre, String direccion, String telefono, String correo, String nit, int estado) {
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
    
    
    
    
      // Método para crear un cliente utilizando un procedimiento almacenado
    public Cliente crearCliente(ConexionBD conexionBD, Cliente cliente) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL CrearCliente(?, ?, ?, ?, ?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cliente.nombre);
                statement.setString(2, cliente.direccion);
                statement.setString(3, cliente.telefono);
                statement.setString(4, cliente.correo);
                statement.setString(5, cliente.nit);
                statement.setInt(6, cliente.estado);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return cliente;
    }
    
    // listar proveedores
    public ArrayList<Cliente> listarCliente(ConexionBD conexionBD){

  
    ArrayList<Cliente> listaclientes = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM clientes";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Cliente  cliente = new Cliente();
                   cliente.setId(resultSet.getInt("id"));
                   cliente.setNit(resultSet.getString("nit"));
                   cliente.setNombre(resultSet.getString("nombre"));
                   cliente.setDireccion(resultSet.getString("direccion"));
                   cliente.setTelefono(resultSet.getString("telefono"));
                   cliente.setCorreo(resultSet.getString("correo"));
                   cliente.setEstado( resultSet.getInt( "estado"));

                    
                    listaclientes.add(cliente);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
     System.out.println(listaclientes);
    return listaclientes;
    };
    
     // Modificar proveedores
    public int modificarCliente(Cliente cliente) throws SQLException{
         String UPDATE_CLIENTE_SQL = "UPDATE Clientes SET nombre = ?, direccion = ?,"
                 + " telefono=?, correo=?, estado = ?, nit = ? WHERE id = ?";
           ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTE_SQL);
            preparedStatement.setInt(7, cliente.id);
            preparedStatement.setString(1, cliente.nombre);
            preparedStatement.setString(2, cliente.direccion);
            preparedStatement.setString(3, cliente.telefono);
            preparedStatement.setString(4, cliente.correo);
            preparedStatement.setInt(5, cliente.estado);
             preparedStatement.setString(6, cliente.nit);
            
            System.out.println(cliente.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
    
}
