package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoJpaRepository;





    public void crearProducto(){
        Producto producto = new Producto("Producto1", 2000);
        productoJpaRepository.save(producto);
    }


    public void editarProducto(){

    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(path = "{index}")
    public String eliminarProducto(@PathVariable int index){




        return "redirect:/carrito";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;

        if (principal instanceof User) {
            user = ((User) principal);
        }
        return user;
    }

}
