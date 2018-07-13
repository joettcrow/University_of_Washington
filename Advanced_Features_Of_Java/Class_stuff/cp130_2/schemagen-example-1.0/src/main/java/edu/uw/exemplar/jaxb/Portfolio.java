package edu.uw.exemplar.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name="portfolio")
public class Portfolio {
	private static final String NL = System.getProperty("line.separator");
	@XmlElement String id;
	//@XmlElementWrapper(name="holdings")
	//@XmlElement(name="holding")
	@XmlElement
	List<Holding> holdings;
	public Portfolio() {
	}

	public Portfolio(String id,	List<Holding> holdings) {
        this.id = id;
        this.holdings = holdings;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Portfolio: ")
		   .append(id);
		for (Holding h : holdings) {
			sb.append(NL)
              .append("    ")
              .append(h);
		}
		return sb.toString();
	}
}
