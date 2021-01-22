package stock_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stock_exchange.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findStatusByStatusName(String name);
}
