/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erp.sistema;

import java.util.Date;

public class Salida {
    
    private Producto producto;
    private int cantidad;
    private Date fecha;
    private Almacen almacen;
    private String motivo;  // Ejemplo: "Venta", "Merma", "Donación", etc.
    
    
    public Salida(){}
    public Salida(Producto producto, int cantidad, Almacen almacen, String motivo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = new Date();  // Fecha actual
        this.almacen = almacen;
        this.motivo = motivo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    

    // ... Getters, setters y otros métodos que puedas necesitar ...

    public void registrarSalida() {
        // Aquí iría el código para registrar la salida en la base de datos o donde sea necesario
    }
}
