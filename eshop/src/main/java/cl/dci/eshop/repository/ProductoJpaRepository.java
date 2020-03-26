package cl.dci.eshop.repository;

import cl.dci.eshop.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductoJpaRepository extends JpaRepository<Producto, Long> {



}
