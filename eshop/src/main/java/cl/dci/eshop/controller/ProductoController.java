package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.model.ProductoCarrito;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.ProductoCarritoRepository;
import cl.dci.eshop.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoRepository productoJpaRepository;
    @Autowired
    private ProductoCarritoRepository productoCarritoRepository;
    @Autowired
    private CarritoRepository carritoRepository;



    @PreAuthorize("hasAuthority('producto:write')")
    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute("producto") Producto producto){

        productoJpaRepository.save(producto);
        return "redirect:/admin/productos";
    }


    @PreAuthorize("hasAuthority('producto:delete')")
    @PostMapping("/delete/{id}")
    public String eliminarProducto(@PathVariable int id){
        List<ProductoCarrito> pcs = productoCarritoRepository.findByProducto(productoJpaRepository.findById(id).orElse(null));
        if (!pcs.isEmpty()) {
            for (ProductoCarrito pc : pcs) {
                Carrito c = pc.getCarrito();
                Producto p = pc.getProducto();
                c.deleteProducto(p);
                carritoRepository.save(c);
                productoCarritoRepository.delete(pc);
            }
        }
        productoJpaRepository.deleteById(id);
        return "redirect:/admin/productos";
    }
    @PreAuthorize("hasAuthority('producto:update')")
    @GetMapping("/update/{id}")
    public String getEditarProducto(@PathVariable int id, Model modelo){

        Producto producto = productoJpaRepository.findById(id).orElse(null);
        modelo.addAttribute("producto", producto);

        return "/admin/producto-update";
    }

    @PreAuthorize("hasAuthority('producto:update')")
    @PostMapping("/update")
    public String editarProducto(@ModelAttribute("pruducto") Producto producto){
        productoJpaRepository.save(producto);
        return "redirect:/admin/productos";
    }




}
