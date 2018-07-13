package edu.uw.exemplar.json;

import java.util.List;
import java.util.Formatter;

public class Portfolio {
	private static final String NL = System.getProperty("line.separator");
    String id;
	List<Holding> holdings;
	public Portfolio() {
	}

	public Portfolio(String id,	List<Holding> holdings) {
        this.id = id;
        this.holdings = holdings;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Holding> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

	public String toString() {
        Formatter fmt = new Formatter(); 
        fmt.format("Portfolio: %s", id);
        for (Holding h : holdings) {
            Stock s = h.getStock();
            fmt.format("%n    %s", h.toString());
        }
        return fmt.toString();
	}
}
