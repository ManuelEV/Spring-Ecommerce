package cl.dci.eshop.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private int precio;

    Producto(){

    }

    public Producto(String nombre, int precio){
        this.nombre = nombre;
        this.precio = precio;
    }

}
