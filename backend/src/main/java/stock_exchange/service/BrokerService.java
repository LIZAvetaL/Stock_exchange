package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;

import java.util.List;
import java.util.Map;

public interface BrokerService {
    List<BrokerDTO> findAll();

    BrokerDTO employBroker(int clientId, int brokerId);

    Deal createDeal(int sellerBidId, int buyerBidId);

    Page<BrokerDTO> findAllUnemployed(String title, int page, int size, String sort);

    BrokerDTO findBroker(int id);

    List<BrokerDTO> findBrokers(int clientId);
}
