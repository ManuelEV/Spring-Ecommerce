package cl.dci.eshop.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "producto")
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
