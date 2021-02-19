package stock_exchange.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import stock_exchange.config.StatusConst;
import stock_exchange.dto.DealDTO;
import stock_exchange.model.Bid;
import stock_exchange.model.Deal;
import stock_exchange.repository.DealRepository;
import stock_exchange.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public void create(Bid sellerBid, Bid buyerBid, double price) {
        Deal deal = new Deal();
        deal.setDealNumber(new Random().ints(10000, 100000).findFirst().getAsInt());
        deal.setAmount(bidAmount(sellerBid.getAmount(), buyerBid.getAmount()));
        deal.setPrice(price);
        deal.setTotalPrice(deal.getAmount() * price);
        deal.setBargainDate(LocalDate.now());
        if (sellerBid.getType().equals(StatusConst.SALE.getName())) {
            deal.setSeller(sellerBid.getBroker());
            deal.setBuyer(buyerBid.getBroker());
        } else {
            deal.setSeller(buyerBid.getBroker());
            deal.setBuyer(sellerBid.getBroker());
        }
        dealRepository.save(deal);
    }

    @Override
    public Page<DealDTO> find(int page, int size, String[] sort, int brokerId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortType(sort)));
        return dealRepository.findDealsBySellerBrokerIdOrBuyerBrokerId(brokerId, brokerId, pageable)
                .map(this::transfer);
    }

    private List<Sort.Order> sortType(String[] sort) {
        List<Sort.Order> list = new ArrayList<>();
        list.add(new Sort.Order(Sort.Direction.fromString(sort[0]), sort[1]));
        return list;
    }

    private int bidAmount(int sellerAmount, int buyerAmount) {
        if (sellerAmount <= buyerAmount) {
            return sellerAmount;
        } else return buyerAmount;

    }

    private DealDTO transfer(Deal deal) {
        DealDTO dealDTO=new DealDTO();
        dealDTO.setId(deal.getId());
        dealDTO.setBargainDate(deal.getBargainDate());
        dealDTO.setAmount(deal.getAmount());
        dealDTO.setPrice(deal.getPrice());
        dealDTO.setTotalPrice(deal.getTotalPrice());
        dealDTO.setDealNumber(deal.getDealNumber());
        dealDTO.setBuyer(deal.getBuyer().getBroker().getName());
        dealDTO.setSeller(deal.getSeller().getBroker().getName());
        return dealDTO;
    }
}
