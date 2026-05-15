package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {

		Trade trade = new Trade();
		trade.setAccount("Trade Account");
		trade.setType("BUY");
		trade.setBuyQuantity(100.0);

		// Save
		trade = tradeRepository.save(trade);

		assertNotNull(trade.getTradeId());
		assertEquals(100.0, trade.getBuyQuantity());

		// Find
		Trade foundTrade = tradeRepository.findById(trade.getTradeId()).orElse(null);

		assertNotNull(foundTrade);
		assertEquals(trade.getTradeId(), foundTrade.getTradeId());

		// Update
		foundTrade.setBuyQuantity(200.0);

		tradeRepository.save(foundTrade);

		Trade updatedTrade = tradeRepository.findById(trade.getTradeId()).orElse(null);

		assertEquals(200.0, updatedTrade.getBuyQuantity());

		// Delete
		Integer id = updatedTrade.getTradeId();

		tradeRepository.delete(updatedTrade);

		assertFalse(tradeRepository.findById(id).isPresent());
	}
}