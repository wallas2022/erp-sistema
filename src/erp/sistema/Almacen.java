
package erp.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Almacen {
    private int id;
    private String nombre;
    private int estado;
    
    public Almacen(){}
    public Almacen(int id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
       // Método para crear un almacen utilizando un procedimiento almacenado
    public Almacen crearAlmacen(ConexionBD conexionBD, Almacen almacen) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL CrearAlmacen(?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, almacen.nombre);
                statement.setInt(2, almacen.estado);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return almacen;
    }
    
    
     // listar almacen
    public ArrayList<Almacen> listarAlmacenes(ConexionBD conexionBD){

  
    ArrayList<Almacen> listaalmacenes = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM almacenes";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Almacen  almacen = new Almacen();
                   almacen.setId(resultSet.getInt("id"));
                   almacen.setNombre(resultSet.getString("nombre"));
                   almacen.setEstado( resultSet.getInt( "estado"));

                    
                    listaalmacenes.add(almacen);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
     System.out.println(listaalmacenes);
    return listaalmacenes;
    };
    
     // Modificar almacen
    public int modificarAlmacen(Almacen almacen) throws SQLException{
         String UPDATE_ALMACEN_SQL = "UPDATE Almacenes SET nombre = ?,  estado = ? WHERE id = ?";
           ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALMACEN_SQL);
            preparedStatement.setInt(3, almacen.id);
            preparedStatement.setString(1, almacen.nombre);
            preparedStatement.setInt(2, almacen.estado);
            
            System.out.println(almacen.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
}
