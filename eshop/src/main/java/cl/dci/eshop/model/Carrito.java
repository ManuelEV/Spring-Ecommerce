package cl.dci.eshop.model;

import cl.dci.eshop.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "carrito")
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carrito_id")
    private int id;
    @Column
    private int cantidadProductos;
    @Column
    private int precioTotal;



    @OneToMany(mappedBy = "carrito")
    private List<ProductoCarrito> productoCarritos;


    @OneToOne()
    @JoinTable(name = "user_carrito",
            joinColumns = {@JoinColumn(name = "carrito_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private User user;

    public Carrito() {
        this.cantidadProductos = 0;
        this.precioTotal = 0;
        this.productoCarritos = new ArrayList<>();

    }

    public void addProducto(Producto producto){
        this.precioTotal += producto.getPrecio();
        this.cantidadProductos++;
    }

    public void deleteProducto(Producto producto){
        this.precioTotal -= producto.getPrecio();
        this.cantidadProductos--;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "id=" + id +
                ", cantidadProductos=" + cantidadProductos +
                ", precioTotal=" + precioTotal +
                //", productos=" + productos +
                //", user=" + user +
                '}';
    }
}
