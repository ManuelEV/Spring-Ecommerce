package cl.dci.eshop.controller;

import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.model.Producto;
import cl.dci.eshop.model.ProductoCarrito;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.ProductoCarritoRepository;
import cl.dci.eshop.repository.ProductoRepository;
import cl.dci.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class TemplateController {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ProductoCarritoRepository productoCarritoRepository;


    @GetMapping()
    public String getRoot() {
        return "redirect:/home";
    }

    @GetMapping("login")
    public String getLogin() {

        return "login";
    }

    @GetMapping("courses")
    public String getCourses() {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = null;

        if (principal instanceof User) {
            username = ((User) principal).getUsername();
            user = ((User) principal);
        } else {
            username = principal.toString();
        }

        System.out.println(user);
        System.out.println(username);

        return "courses";
    }

    @GetMapping("catalogo")
    public String getCatalogo(Model modelo) {

        basicSetup(modelo, "Catálogo");
        modelo.addAttribute("productos", productoRepository.findAll());
        modelo.addAttribute("carrito", carritoRepository.findById(42).orElse(null));


        return "catalogo";
    }

    @GetMapping(path = "producto/{idProducto}")
    public String getProducto(@PathVariable("idProducto") Integer idProducto, Model modelo) {
        Producto producto = productoRepository.findById(idProducto).orElse(null);
        basicSetup(modelo, "Producto: "+producto.getNombre());

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("usuarios", userRepository.findAll());

        return "producto";
    }

    @PreAuthorize("hasAuthority('carrito:manage')")
    @GetMapping("carrito")
    public String getCarrito(Model modelo) {
        basicSetup(modelo, "Carrito");

        Carrito carrito = getCurrentUser().getCarrito();

        modelo.addAttribute("carrito", carrito);
        modelo.addAttribute("prodCars", getProductoCarritos());
        return "carrito";
    }

    @GetMapping("checkout")
    public String getCheckout(Model modelo) {
        basicSetup(modelo, "Checkout");
        return "checkout";
    }

    @GetMapping("home")
    public String getHome(Model modelo) {
        basicSetup(modelo, "Home");


        return "home";
    }


    @GetMapping("registro")
    public String getRegistro(Model modelo) {
        modelo.addAttribute("titulo", "Registro");
        modelo.addAttribute("usuario", new User());
        return "registro";
    }

    @PreAuthorize("hasAuthority('perfil:manage')")
    @GetMapping("perfil")
    public String getPerfil(Model modelo) {

        User user = getCurrentUser();

        basicSetup(modelo, "Perfil");
        modelo.addAttribute("usuario", user);

        return "perfil";
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;

        if (principal instanceof User) {
            user = ((User) principal);
        }
        return user;
    }

    //Sección admin


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("admin/usuarios")
    public String getAdminUsuarios(Model modelo) {
        basicSetup(modelo, "Administrar usuarios");
        List<User> users = userRepository.findAll();
        modelo.addAttribute("usuarios", users);
        modelo.addAttribute("usuario", new User());
        return "admin/admin-usuarios";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("admin/productos")
    public String getAdminProductos(Model modelo) {
        basicSetup(modelo, "administrar productos");
        List<Producto> productos = productoRepository.findAll();
        modelo.addAttribute("productos", productos);

        modelo.addAttribute("producto", new Producto());
        return "admin/admin-productos";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("admin/pedidos")
    public String getAdminPedidos(Model modelo) {
        basicSetup(modelo, "Administrar pedidos");
        return "admin/admin-pedidos";
    }

    private Model basicSetup(Model modelo, String titulo){
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("usuarioLogueado", this.usuarioLogueado());
        String rol = usuarioLogueado() ? getCurrentUser().getRole().name():"";
        modelo.addAttribute("rolUsuario", rol);

        return modelo;
    }


    @GetMapping("poblamiento")
    public String getPoblamiento(Model modelo) {
        modelo.addAttribute("usuarioLogueado", this.usuarioLogueado());
        //testProductoCarrito();
        this.poblarBd();
        return "catalogo";
    }

    private boolean usuarioLogueado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return !principal.toString().equals("anonymousUser");
    }



    private void testProductoCarrito(){
        Producto p = productoRepository.findById(9).orElse(null);
        Carrito c = carritoRepository.findById(5).orElse(null);
        /*ProductoCarrito pc1 = new ProductoCarrito(p, c);
        ProductoCarrito pc2 = new ProductoCarrito(p, c);
        productoCarritoRepository.save(pc1);
        productoCarritoRepository.save(pc2);*/
        //System.out.println(productoCarritoRepository.findByCarrito(c));
        List<ProductoCarrito> pcs =productoCarritoRepository.findByCarrito(c);
        for(ProductoCarrito pc: pcs){
            System.out.println(pc.getProducto());
        }
    }

    private List<ProductoCarrito> getProductoCarritos(){
        Carrito carrito = getCurrentUser().getCarrito();
        //System.out.println("TESTINGGGGGG"+productoCarritoRepository.findByCarrito(carrito).size());
        return productoCarritoRepository.findByCarrito(carrito);
    }


    private void poblarBd() {

        Producto p1 = new Producto("Producto 1", 2000);
        Producto p2 = new Producto("Producto 2", 2500);
        Producto p3 = new Producto("Producto 3", 3000);
        Producto p4 = new Producto("Producto 4", 5000);



        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);
        productoRepository.save(p4);



        /*User user = getCurrentUser();
        carrito.setUser(user);
        System.out.println(carrito);*/

        //carritoRepository.save(carrito);
        /*User u = userRepository.findById(user.getId()).orElse(null);

        u.setCarrito(carrito);
        userRepository.save(u);
        System.out.println(u);*/
    }

}
