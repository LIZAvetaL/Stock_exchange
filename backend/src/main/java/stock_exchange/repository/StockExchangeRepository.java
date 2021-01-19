package stock_exchange.repository;

import stock_exchange.model.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import stock_exchange.model.User;

import java.util.List;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
    List<StockExchange> findStockExchangesByOwner(User ownerId);
}
