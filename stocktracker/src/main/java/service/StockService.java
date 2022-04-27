package service;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import model.StockWrapper;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@AllArgsConstructor
@Service
public class StockService {
	
	public StockWrapper findStock(final String ticker) {
		try {
			return new StockWrapper(YahooFinance.get(ticker));
		}
		catch(IOException e) {
			System.out.println("Error");
		}
		return null;
	}

	public BigDecimal findPrice(final StockWrapper stock) throws IOException{
		return stock.getStock().getQuote(true).getPrice();
	}
	
	
	public static void main(String[] args) throws IOException {

		Stock stock = YahooFinance.get("INTC");
		
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
		
		//stock.print();
		System.out.println(price);
	}
}

