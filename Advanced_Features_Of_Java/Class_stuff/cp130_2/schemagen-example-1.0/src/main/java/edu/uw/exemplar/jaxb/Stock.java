package edu.uw.exemplar.jaxb;

import javax.xml.bind.annotation.XmlAttribute;

public class Stock {
	@XmlAttribute(name="stockSymbol")
	private String stockSymbol;
	
    private int currentSharePrice;
	
	public Stock() {
	}
	
    public Stock(String stockSymbol, int currentSharePrice) {
		this.stockSymbol = stockSymbol;
	    this.currentSharePrice = currentSharePrice;
	}

	public String ticker() {
		return stockSymbol;
	}

	public void ticker(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	@XmlAttribute(name="currentSharePrice")
	public int getCurrentSharePrice() {
		return currentSharePrice;
	}

	public void setCurrentSharePrice(int currentSharePrice) {
		this.currentSharePrice = currentSharePrice;
	}

	public String toString() {
		return stockSymbol + " @ " + currentSharePrice;
	}
}
