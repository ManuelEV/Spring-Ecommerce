package cl.dci.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("courses")
    public String getCourses() {
        return "courses";
    }

    @GetMapping("catalogo")
    public String getCatalogo() {
        return "catalogo";
    }

    @GetMapping("carrito")
    public String getCarrito() {
        return "carrito";
    }

    @GetMapping("checkout")
    public String getCheckout() {
        return "checkout";
    }

    @GetMapping("home")
    public String getHome() {
        return "home";
    }

    @GetMapping("registro")
    public String getRegistro() {
        return "registro";
    }

    //Sección admin

    @GetMapping("admin/perfil")
    public String getPerfilAdmin() {
        return "adminperfil";
    }

    @GetMapping("admin/usuarios")
    public String getAdminUsuarios() {
        return "adminusuarios";
    }

    @GetMapping("admin/productos")
    public String getAdminProductos() {
        return "registro";
    }

    @GetMapping("admin/pedidos")
    public String getAdminPedidos() {
        return "registro";
    }

}
