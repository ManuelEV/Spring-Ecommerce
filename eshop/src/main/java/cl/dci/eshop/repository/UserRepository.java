package cl.dci.eshop.repository;


import cl.dci.eshop.auth.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUserDetails, Integer> {
    Optional<CustomUserDetails> findByUsername(String username);
}
