package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_exchange.model.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import stock_exchange.model.Status;

import java.util.List;

public interface BrokerRepository extends JpaRepository<Broker, Integer> {

    Page<Broker> findAllByStatus(Status status, Pageable pageable);
    List<Broker> findBrokersByEmployerId(int employerId);
}
