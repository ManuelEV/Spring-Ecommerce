package cl.dci.eshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "producto_carrito")
@Data @NoArgsConstructor
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


    public ProductoCarrito(Producto producto, Carrito carrito) {
        this.producto = producto;
        this.carrito = carrito;
    }

}
