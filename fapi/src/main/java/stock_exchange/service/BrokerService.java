package stock_exchange.service;
import stock_exchange.model.Broker;
import stock_exchange.model.BrokerStatisticsDTO;
import stock_exchange.model.StockExchange;
import stock_exchange.model.UnemployedBroker;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;

import java.util.List;

public interface BrokerService {
   PageResponse<UnemployedBroker> findAllUnemployed(String title, int page, int size, String sort);

    PageResponse<Broker> findBrokers(int page, int size, int clientId);

    List<Broker> findBrokers( int clientId);

    MessageResponse employ(int brokerId, int clientId);

    MessageResponse dismiss(int brokerId);

    PageResponse<BrokerStatisticsDTO> getStatistics(int page, int size, int clientId);

    MessageResponse update(int brokerId, String exchange);
}
