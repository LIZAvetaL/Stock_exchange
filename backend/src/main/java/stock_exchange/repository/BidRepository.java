package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_exchange.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    Page<Bid> findBidsByBrokerId(int id, Pageable pageable);

    Page<Bid> findBidsByClientId(int clientId, Pageable pageable);
}
