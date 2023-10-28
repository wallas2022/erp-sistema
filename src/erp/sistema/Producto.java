
package erp.sistema ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Producto {
    private int id ;
    private String codigo;
    private String nombre;
    private double precioCosto;
    private double precioVenta;
    private int cantidadStock;
    private int maximos;
    private int minimos;
    private int estado;
    Categorias categoria;

  
    public Producto(){}

    public Producto(int id, String codigo, String nombre, double precioCosto, double precioVenta, int cantidadStock, int maximos, int minimos, int estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.cantidadStock = cantidadStock;
        this.maximos = maximos;
        this.minimos = minimos;
        this.estado = estado;
        
    }

    

    // Métodos getter y setter para los atributos
    public void setId(int id){
      this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPrecioCosto(Double precioCosto){
    
        this.precioCosto = precioCosto;
    }
    public double getPrecioCosto() {
        return precioCosto;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setPrecio(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public int isEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    public int getEstado(){
        return estado;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public int getMaximos() {
        return maximos;
    }

    public void setMaximos(int maximos) {
        this.maximos = maximos;
    }

    public int getMinimos() {
        return minimos;
    }

    public void setMinimos(int minimos) {
        this.minimos = minimos;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    
    /**
     * Actualiza el stock del producto en la base de datos.
     * 
     * @param connection la conexión a la base de datos.
     * @param nuevoStock el nuevo stock del producto.
     * @return true si se actualizó con éxito, false en caso contrario.
     */
    public boolean actualizarStock(ConexionBD conexionBD, int nuevoStock,int productoid) {
        String query = "UPDATE productos SET cantidadStock = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conexionBD.conectar().prepareStatement(query)) {
            preparedStatement.setInt(1, nuevoStock);
            preparedStatement.setInt(2, productoid);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                this.cantidadStock = nuevoStock; // Actualizamos el valor en el objeto actual.
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // Método para incrementar la cantidad en stock
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidadStock += cantidad;
        }
    }

    // Método para reducir la cantidad en stock
    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= this.cantidadStock) {
            this.cantidadStock -= cantidad;
        }
    }

    // Método para calcular el valor total en inventario (cantidad en stock * precio)
    public double calcularValorEnInventario() {
        return precioVenta * cantidadStock;
    }
    
    
    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    // Representación en cadena del producto
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precioVenta + ", Stock: " + cantidadStock;
    }
    
      // Método para crear un producto utilizando un procedimiento almacenado
    public Producto crearProducto(ConexionBD conexionBD, Producto producto) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL insertarProducto(?, ?, ?,?,?,?,?,?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, producto.codigo);
                statement.setString(2, producto.nombre);
                statement.setDouble(3,producto.precioCosto);
                statement.setDouble(4,producto.precioVenta);
                statement.setInt(5,producto.cantidadStock);
                statement.setInt(6,producto.maximos);
                statement.setInt(7,producto.minimos);
                statement.setInt(8,producto.estado);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return producto;
    }
    
    
     // listar productos
    public ArrayList<Producto> listarProductos(ConexionBD conexionBD){

  
    ArrayList<Producto> listaproductos = new ArrayList<>();
  
    try (Connection connection = conexionBD.conectar()) {
        
        String sql = "SELECT * FROM productos";
         try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                 while (resultSet.next()) {
                   Producto  producto = new Producto();
                   producto.setId(resultSet.getInt("id"));
                   producto.setCodigo(resultSet.getString("codigo"));
                   producto.setNombre(resultSet.getString("nombre"));
                   producto.setPrecioCosto(resultSet.getDouble( "preciocosto"));
                   producto.setPrecioVenta( resultSet.getDouble( "precioVenta"));
                   producto.setCantidadStock(resultSet.getInt("cantidadStock"));
                   producto.setMaximos(resultSet.getInt("maximos"));
                   producto.setMinimos(resultSet.getInt("minimos"));
                   producto.setEstado(resultSet.getInt( "estado"));
                  

                    
                    listaproductos.add(producto);
                }
            }
    } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
     System.out.println(listaproductos);
    return listaproductos;
    };
    
      // Modificar producto
    public int modificarProducto(Producto producto) throws SQLException{
         String UPDATE_PRODUCTO_SQL = "UPDATE productos SET codigo = ?, nombre = ?, precioCosto = ?, precioVenta = ?, cantidadStock = ?, maximos = ?, minimos =?, estado = ?"
                 
                 + " WHERE id = ?";
           ConexionBD conn = new ConexionBD();
           Connection connection = null;
           connection = conn.conectar();
          
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTO_SQL);
            preparedStatement.setInt(9, producto.id);
            preparedStatement.setString(1, producto.codigo);
            preparedStatement.setString(2, producto.nombre);
            preparedStatement.setDouble(3, producto.precioCosto);
            preparedStatement.setDouble(4, producto.precioVenta);
            preparedStatement.setInt(5, producto.cantidadStock);
            preparedStatement.setInt(6,producto.maximos);
            preparedStatement.setInt(7,producto.minimos);
            preparedStatement.setInt(8,producto.estado);
            
            System.out.println(producto.toString());

            int rowsUpdated = preparedStatement.executeUpdate();
            
        System.out.println(rowsUpdated);
      
  
    return rowsUpdated;
    
    };
    
    
 // listar producto
    public int mostrarProducto(ConexionBD conexionBD, String codigo) {

         String sql = "SELECT * FROM productos WHERE codigo = ?";
        try (PreparedStatement stmt = conexionBD.conectar().prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setCodigo(rs.getString("codigo"));
                    // Set other attributes as well
                    return producto.getId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O manejar la excepción de manera más adecuada
        }
        return 0; // Devuelve null si no encuentra el almacén
  
    }
    
      
}
