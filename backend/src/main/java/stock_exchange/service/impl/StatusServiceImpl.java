package stock_exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock_exchange.model.Status;
import stock_exchange.repository.StatusRepository;
import stock_exchange.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Status find(String name) {
        return statusRepository.findStatusByStatusName(name);
    }
}
