/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erp.sistema;

/**
 *
 * @author Luisa Navarro
 */
public class inv_clasificaciones {
    private int id;
    private String nombre;
    private String descripcion;
    
    
public inv_clasificaciones(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

     public String getDescripcion() {
        return descripcion;
    }
     
      public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
      }
      