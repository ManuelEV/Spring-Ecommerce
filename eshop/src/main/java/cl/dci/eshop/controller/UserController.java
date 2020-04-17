package cl.dci.eshop.controller;


import cl.dci.eshop.auth.User;
import cl.dci.eshop.model.Carrito;
import cl.dci.eshop.repository.CarritoRepository;
import cl.dci.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static cl.dci.eshop.security.ApplicationUserRole.*;

@Controller
@RequestMapping("/api/usuario")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasAuthority('usuario:write')")
    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") User usuario){

        Carrito c1 = new Carrito();
        c1.setUser(usuario);
        System.out.println("USUARIO: "+usuario);
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        userRepository.save(usuario);
        carritoRepository.save(c1);
        return "redirect:/admin/productos";
    }

    

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") User usuario){

        Carrito c1 = new Carrito();
        c1.setUser(usuario);
        System.out.println("USUARIO: "+usuario);
        String password = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(password);
        usuario.setRole(CUSTOMER);
        userRepository.save(usuario);
        carritoRepository.save(c1);
        System.out.println("USUARIO: "+usuario);
        return "redirect:/";
    }

    @GetMapping
    public String getAllStudents() {
        String password = passwordEncoder.encode("password");

        User user1 = new User("customer1",
                password,
        CUSTOMER);

        User user2 = new User("customer2",
                password,
                CUSTOMER);
        User user3 = new User("customer3",
                password,
                CUSTOMER);
        User user4 = new User("admin",
                password,
                ADMIN);

        Carrito c1 = new Carrito();
        Carrito c2 = new Carrito();
        Carrito c3 = new Carrito();
        Carrito c4 = new Carrito();

        c1.setUser(user1);
        c2.setUser(user2);
        c3.setUser(user3);
        c3.setUser(user4);

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.save(user4);

        this.carritoRepository.save(c1);
        this.carritoRepository.save(c2);
        this.carritoRepository.save(c3);
        this.carritoRepository.save(c4);

        System.out.println("OK BRO");
        return "redirect:/home";
    }

}
