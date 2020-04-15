package cl.dci.eshop.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductoCarritoKey implements Serializable {

    @Column(name = "producto_id")
    private int productoId;
    @Column(name = "carrito_id")
    private int carritoId;



    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(int carritoId) {
        this.carritoId = carritoId;
    }
}
