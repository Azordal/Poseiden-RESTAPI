package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public BidList save(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id: " + id));
    }

    public BidList update(Integer id, BidList bidList) {

        BidList existingBid = findById(id);

        existingBid.setAccount(bidList.getAccount());
        existingBid.setType(bidList.getType());
        existingBid.setBidQuantity(bidList.getBidQuantity());

        return bidListRepository.save(existingBid);
    }

    public void delete(Integer id) {
        BidList bidList = findById(id);
        bidListRepository.delete(bidList);
    }
}