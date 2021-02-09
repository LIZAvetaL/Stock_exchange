package stock_exchange.service;

import org.springframework.data.domain.Page;
import stock_exchange.dto.BidDTO;
import stock_exchange.dto.BrokerDTO;
import stock_exchange.dto.UnemployedBrokerDTO;
import stock_exchange.model.Broker;
import stock_exchange.model.Deal;
import stock_exchange.response.MessageResponse;
import sun.security.util.ManifestEntryVerifier;

import java.util.List;
import java.util.Map;

public interface BrokerService {
    List<BrokerDTO> findAll();

    Page<UnemployedBrokerDTO> findAllUnemployed(String title, int page, int size, String sort);

    BrokerDTO findBroker(int id);

    Broker findBroker(String name);

    Broker find(int id);

    Page<BrokerDTO> findBrokers(int page, int size,int clientId);

    List<BrokerDTO> findBrokers(int clientId);

    MessageResponse employ(int brokerId, int clientId);

    MessageResponse dismiss(int brokerId);
}
