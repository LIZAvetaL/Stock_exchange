package stock_exchange.service;

import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.model.Deal;

import java.util.List;
import java.util.Map;

public interface BrokerService {
    List<BrokerDTO> findAll();

    BrokerDTO employBroker(int clientId, int brokerId);

    List<BidDTO> findBrokersBids(int id);

    Deal createDeal(int sellerBidId, int buyerBidId);

    Map<String, Object> findAllUnemployed(String title, int page, int size, String sort);

    BrokerDTO findBroker(int id);
}
