package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidTests {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {

		BidList bid = new BidList();
		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10.0);

		// Save
		bid = bidListRepository.save(bid);

		assertNotNull(bid.getBidListId());
		assertEquals(10.0, bid.getBidQuantity());

		// Find
		BidList foundBid = bidListRepository.findById(bid.getBidListId()).orElse(null);

		assertNotNull(foundBid);
		assertEquals(bid.getBidListId(), foundBid.getBidListId());

		// Update
		foundBid.setBidQuantity(20.0);

		bidListRepository.save(foundBid);

		BidList updatedBid = bidListRepository.findById(bid.getBidListId()).orElse(null);

		assertEquals(20.0, updatedBid.getBidQuantity());

		// Delete
		Integer id = updatedBid.getBidListId();

		bidListRepository.delete(updatedBid);

		assertFalse(bidListRepository.findById(id).isPresent());
	}
}