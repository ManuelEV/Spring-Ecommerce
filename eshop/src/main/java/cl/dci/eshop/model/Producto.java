package cl.dci.eshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private int precio;


    /*@ManyToMany(mappedBy = "productos", fetch = FetchType.EAGER)
    private List<Carrito> carritos;*/

    @OneToMany(mappedBy = "producto", cascade = CascadeType.MERGE)
    private List<ProductoCarrito> productoCarritos;

    public Producto(){
        //this.carritos = new ArrayList<>();
        this.productoCarritos = new ArrayList<>();
    }

    public Producto(String nombre, int precio){
        this.nombre = nombre;
        this.precio = precio;
        //this.carritos = new ArrayList<>();
        this.productoCarritos = new ArrayList<>();
    }

    public List<ProductoCarrito> getProductoCarritos() {
        return productoCarritos;
    }

    public void setProductoCarritos(List<ProductoCarrito> productoCarritos) {
        this.productoCarritos = productoCarritos;
    }
/*
    public void addCarrito(Carrito carrito){
        this.carritos.add(carrito);
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }*/

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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                //", carritos=" + carritos +
                '}';
    }
}
