package stock_exchange.repository;

import stock_exchange.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByName(String name);

    boolean existsUserByEmail(String email);
}
