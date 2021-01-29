package stock_exchange.service;
import stock_exchange.model.Broker;
import stock_exchange.model.UnemployedBroker;
import stock_exchange.model.response.MessageResponse;
import stock_exchange.model.response.PageResponse;

import java.util.List;

public interface BrokerService {
   PageResponse<UnemployedBroker> findAllUnemployed(String title, int page, int size, String sort);

    List<Broker> findBrokers(int clientId);

    MessageResponse employ(int brokerId, int clientId);
}
