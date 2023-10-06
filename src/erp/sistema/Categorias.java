
package erp.sistema;


public class Categorias {
    private int id;
    private String nombre;
    private String descripción;
    private int estado;

    public Categorias(int id, String nombre, String descripción, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripción = descripción;
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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
   
    
}
