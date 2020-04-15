package cl.dci.eshop.repository;

import cl.dci.eshop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductoRepository extends JpaRepository<Producto, Integer> {



}
