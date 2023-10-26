
package erp.sistema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Categorias {
    private int id;
    private String nombre;
    private String descripcion;
    private int estado;

    public Categorias(int id, String nombre, String descripcion, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

   Categorias(){};
    
    
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
      // Método para crear un usuario utilizando un procedimiento almacenado
    public Categorias crearCategoria(ConexionBD conexionBD, Categorias categoria) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL CrearCategoria(?, ?, ?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, categoria.nombre);
                statement.setString(2, categoria.descripcion);
                statement.setInt(3, categoria.estado);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return categoria;
    }
    
    // listar categorias
    public ArrayList<Categorias> listarCategorias(ConexionBD conexionBD){

  
    ArrayList<Categorias> listacategorias = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM categorias";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Categorias  categoria = new Categorias();
                   categoria.setId(resultSet.getInt("id"));
                   categoria.setNombre(resultSet.getString("nombre"));
                   categoria.setDescripcion(resultSet.getString("descripcion"));
                   categoria.setEstado( resultSet.getInt( "estado"));

                    
                    listacategorias.add(categoria);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
     System.out.println(listacategorias);
    return listacategorias;
    };
    
     // Modificar usuario
    public int modificarCategoria(Categorias categoria) throws SQLException{
         String UPDATE_CATEGORIA_SQL = "UPDATE Categorias SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?";
           ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORIA_SQL);
            preparedStatement.setInt(4, categoria.id);
            preparedStatement.setString(1, categoria.nombre);
            preparedStatement.setString(2, categoria.descripcion);
            preparedStatement.setInt(3, categoria.estado);
            
            System.out.println(categoria.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
   
    
}
