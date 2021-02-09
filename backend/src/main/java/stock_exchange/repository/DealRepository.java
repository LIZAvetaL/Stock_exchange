package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stock_exchange.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Integer> {

    Page<Deal> findDealsBySellerBrokerIdOrBuyerBrokerId(int sellerId, int buyerId, Pageable pageable);
}
