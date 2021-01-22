package stock_exchange.service;

import stock_exchange.model.Status;

public interface StatusService {
    Status find(String name);
}
