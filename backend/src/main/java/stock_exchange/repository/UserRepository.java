package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_exchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByName(String name);

    Page<User> findAll(Pageable pageable);

    boolean existsUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
