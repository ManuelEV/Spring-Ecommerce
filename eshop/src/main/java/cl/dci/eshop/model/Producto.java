package cl.dci.eshop.model;



import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nombre;
    @Column
    private int precio;



    @OneToMany(mappedBy = "producto", cascade = CascadeType.MERGE)
    private List<ProductoCarrito> productoCarritos;

    public Producto(){
        this.productoCarritos = new ArrayList<>();
    }

    public Producto(String nombre, int precio){
        this.nombre = nombre;
        this.precio = precio;
        this.productoCarritos = new ArrayList<>();
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
