/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erp.sistema;


public class Entrada extends Cardex {
    
    
    private int proveedor;
    private double precioCompra;
   
  
    
    public Entrada(){}
    public Entrada(int proveedor, double precioCompra) {
      
        this.proveedor = proveedor;
        this.precioCompra = precioCompra;
     
    }
    

    
    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

   
    // ... Getters, setters y otros métodos que puedas necesitar ...

    public void registrarEntrada() {
        // Aquí iría el código para registrar la entrada en la base de datos o donde sea necesario
    }

    @Override
    public void registroEntrada() {
   }
}
