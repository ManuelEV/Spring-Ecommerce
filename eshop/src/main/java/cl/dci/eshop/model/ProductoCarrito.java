package cl.dci.eshop.model;

import javax.persistence.*;

@Entity
@Table(name = "producto_carrito")
public class ProductoCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    public ProductoCarrito() {
    }

    public ProductoCarrito(Producto producto, Carrito carrito) {
        this.producto = producto;
        this.carrito = carrito;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
    public ProductoCarritoKey getId() {
        return id;
    }

    public void setId(ProductoCarritoKey id) {
        this.id = id;
    }*/

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}
