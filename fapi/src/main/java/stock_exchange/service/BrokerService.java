package stock_exchange.service;
import java.util.Map;

public interface BrokerService {
    Map<String, Object> findAllUnemployed(String title, int page, int size, String sort);
}
