
package erp.sistema;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


abstract class Cardex {
    private int id;
    private int producto;
    private int almacen;
    private int proveedor;
    private Date fecha;
    private TipoTransaccion tipo; // lista enum: ENTRADA, SALIDA
    private int cantidad;
    private int saldo;
    private String motivo;

    // Constructores
    public abstract void registroEntrada();
    public Cardex(){}
    public Cardex(int id, int producto, int almacen,int proveedor,  Date fecha, TipoTransaccion tipo, int cantidad, int saldo, String motivo) {
        this.id = id;
        this.producto = producto;
        this.almacen = almacen;
        this.producto = proveedor;
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

  

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    
    public enum TipoTransaccion {
    ENTRADA, SALIDA;
}
    
      // Método para crear un cardex utilizando un procedimiento almacenado
    public Cardex crearIngreso(ConexionBD conexionBD, Cardex cardex) {
        try (Connection connection = conexionBD.conectar()) {
            String sql = "{CALL sp_crearEntrada(?, ?, ?,?,?,?)}";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, cardex.producto);
                statement.setInt(2, cardex.almacen);
                statement.setInt(3,cardex.proveedor);
                statement.setDate(4, new java.sql.Date(new Date().getTime()));
                statement.setInt(5,cardex.cantidad);
                statement.setString(6,cardex.motivo);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionBD.desconectar();
        }
        return cardex;
    }


}
