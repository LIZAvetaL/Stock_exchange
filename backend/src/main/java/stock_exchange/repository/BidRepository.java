package stock_exchange.repository;

import stock_exchange.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    List<Bid> findBidsByBrokerId(int id);
}
