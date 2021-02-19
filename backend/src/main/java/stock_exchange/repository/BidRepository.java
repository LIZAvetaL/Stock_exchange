package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_exchange.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    Page<Bid> findBidsByBrokerIdAndStatusStatusName(int id, String statusName, Pageable pageable);

    Page<Bid> findBidsByClientId(int clientId, Pageable pageable);

    Page<Bid> findBidsByStatusStatusName(String statusName, Pageable pageable);

    Page<Bid> findBidsByStatusStatusNameAndIssuer(String statusName, String issuer, Pageable pageable);

    List<Bid> findBidsByStatusStatusNameAndIssuerAndType(String statusName, String issuer, String type);

    List<Bid> findBidsByClientIdAndBrokerIdAndStatusStatusName(int clientId, int brokerId, String statusName);

    Boolean existsBidsByBrokerIdAndStatusStatusName(int id, String name);
}