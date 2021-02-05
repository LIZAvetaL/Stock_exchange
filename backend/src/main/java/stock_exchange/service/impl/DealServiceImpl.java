package stock_exchange.service.impl;

import stock_exchange.config.StatusConst;
import stock_exchange.model.Bid;
import stock_exchange.model.Deal;
import stock_exchange.repository.DealRepository;
import stock_exchange.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Override
    public void create(Bid sellerBid, Bid buyerBid, double price) {
        Deal deal = new Deal();
        deal.setAmount(bidAmount(sellerBid.getAmount(), buyerBid.getAmount()));
        deal.setPrice(price);
        deal.setTotalPrice(deal.getAmount() * price);
        deal.setBargainDate(LocalDate.now());
        if (sellerBid.getType().equals(StatusConst.Sale.toString())) {
            deal.setSeller(sellerBid.getBroker());
            deal.setBuyer(buyerBid.getBroker());
        } else {
            deal.setSeller(buyerBid.getBroker());
            deal.setBuyer(sellerBid.getBroker());
        }
        dealRepository.save(deal);
    }

    private int bidAmount(int sellerAmount, int buyerAmount) {
        if (sellerAmount >= buyerAmount) {
            return sellerAmount;
        } else return buyerAmount;

    }
}
