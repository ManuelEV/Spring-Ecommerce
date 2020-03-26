package cl.dci.eshop.controlador;

import cl.dci.eshop.modelo.Producto;
import cl.dci.eshop.repository.ProductoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoJpaRepository productoJpaRepository;

    @RequestMapping("/listar")
    public List<Producto> getProductos(){
        return productoJpaRepository.findAll();
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public void addProducto(){
        Producto producto = new Producto("Producto1", 2000);
        productoJpaRepository.save(producto);
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public void editarProducto(){

    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public void eliminarProducto(){

    }

}
