package stock_exchange.service.impl;

import stock_exchange.model.Deal;
import stock_exchange.repository.DealRepository;
import stock_exchange.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public Deal getDeal(int id) {
        return dealRepository.findById(id).get();
    }

    @Override
    public void save(Deal deal) {
        dealRepository.save(deal);
    }
}
