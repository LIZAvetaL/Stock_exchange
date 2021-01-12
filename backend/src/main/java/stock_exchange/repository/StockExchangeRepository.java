package stock_exchange.repository;

import stock_exchange.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
    List<StockExchange> findStockExchangeByOwner(int ownerId);
}