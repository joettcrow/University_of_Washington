package edu.uw.exemplar.json;

public class Holding {
	private Stock stock;
    private int shares;
	
	public Holding() {
	}
	
    public Holding(Stock stock, int shares) {
		this.stock = stock;
		this.shares = shares;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public String toString() {
        return String.format("%d shares of %s", shares, stock);
	}
}
