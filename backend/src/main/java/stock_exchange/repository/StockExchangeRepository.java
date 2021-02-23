package stock_exchange.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stock_exchange.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
    Page<StockExchange> findStockExchangesByOwnerId(int ownerId, Pageable pageable);

    List<StockExchange> findStockExchangesByStatusStatusName(String statusName);

    StockExchange findStockExchangeByExchangeName(String name);

    boolean existsStockExchangeByExchangeName(String name);

    @Query(" SELECT d.bargainDate as date, count (d.id) as countOfDeals " +
            "FROM Deal d JOIN Broker b on (b.id = d.buyer.id or b.id=d.seller.id) " +
            "and b.exchange.id = :exchangeId" +
            " group by d.bargainDate\n" +
            "order by d.bargainDate desc")
    Page<Object[]> findStatistics(@Param("exchangeId") int exchangeId, Pageable pageable);
}
