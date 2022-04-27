package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import model.StockWrapper;

@SpringBootTest
public class StockServiceTest {

	@Autowired
	private StockService stockService;
	
	@Test
	public void invoke() {
		final StockWrapper stock = stockService.findStock("F");
		System.out.println(stock.getStock());
	}
}
