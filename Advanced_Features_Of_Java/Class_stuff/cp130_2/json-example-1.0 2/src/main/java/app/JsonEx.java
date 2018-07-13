package app;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uw.exemplar.json.Holding;
import edu.uw.exemplar.json.Portfolio;
import edu.uw.exemplar.json.Stock;


public class JsonEx {

	public static void main(String[] args) throws Exception {
		// Create the portfolio object graph
        ArrayList<Holding> holdings = new ArrayList<Holding>();
        holdings.add(new Holding(new Stock("AAPL", 13000), 10000));
        holdings.add(new Holding(new Stock("MSFT", 11000), 1000));
        holdings.add(new Holding(new Stock("F", 5400), 5000));
        Portfolio p = new Portfolio("Abc123", holdings);
        // Print the original
        System.out.println("Created portfolio.");
        System.out.println(p);
        
        final String filename = "portfolio.json";
        // Create a file
        System.out.printf("Writing portfolio to file, %s%n", filename);
        File file = new File(filename);
        
        // Write it out
        ObjectMapper mapper = new ObjectMapper(); 
        mapper.writeValue(file, p);

        System.out.printf("Reading portfolio from file, %s%n", filename);
        
        Portfolio px = mapper.readValue(file, Portfolio.class);
        // Print the copy
        System.out.println(px);          
	}
}
