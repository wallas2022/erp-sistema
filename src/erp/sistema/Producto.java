
package erp.sistema;

public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private double precioCosto;
    private double precioVenta;
    private int cantidadStock;
    private int maximos;
    private int minimos;
    private boolean estado;
  
    

    public Producto(int id, String codigo, String nombre, double precioCosto, double precioVenta, int cantidadStock, int maximos, int minimos, boolean estado) {
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
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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

    // Representación en cadena del producto
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precioVenta + ", Stock: " + cantidadStock;
    }
}
