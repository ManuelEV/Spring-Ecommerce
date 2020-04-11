package cl.dci.eshop.repository;

import cl.dci.eshop.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
