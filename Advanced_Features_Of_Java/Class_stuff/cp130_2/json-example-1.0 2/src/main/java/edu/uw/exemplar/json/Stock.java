package edu.uw.exemplar.json;

public class Stock {
    private String stockSymbol;
    private int currentSharePrice;
    
    public Stock() {
    }
    
    public Stock(String stockSymbol, int currentSharePrice) {
        this.stockSymbol = stockSymbol;
        this.currentSharePrice = currentSharePrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getCurrentSharePrice() {
        return currentSharePrice;
    }

    public void setCurrentSharePrice(int currentSharePrice) {
        this.currentSharePrice = currentSharePrice;
    }

    public String toString() {
        return String.format("%s @ %d", stockSymbol, currentSharePrice);
    }
}
