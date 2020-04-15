package cl.dci.eshop.repository;

import cl.dci.eshop.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
