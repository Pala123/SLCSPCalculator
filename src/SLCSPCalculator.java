import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SLCSPCalculator {
	
	private static final Map<Integer, Set<String>> ZIPCODE_TO_RATEAREA = new HashMap<>();
	
	protected static final Map<String, Set<Double>> RATEAREA_TO_RATES = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		populateZipCodeToRateAreaMap();
		
		populateRateAreaToRatesMap();
		
		calculateSLCSP();
	}
	
	/* Method to read Zips.csv file and map RateAreas to the corresponding zip codes */
	private static void populateZipCodeToRateAreaMap() throws FileNotFoundException, IOException {
		int lineCount = 1;
		try (BufferedReader br = new BufferedReader(new FileReader("zips.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lineCount != 1) {
					String[] row = line.split(",");
					Integer zipCode = Integer.valueOf(row[0]);
					Set<String> rateAreas = ZIPCODE_TO_RATEAREA.getOrDefault(zipCode, new HashSet<>());
					rateAreas.add(row[4]);
					ZIPCODE_TO_RATEAREA.put(zipCode, rateAreas);
				}
				lineCount++;
			}
		}
	}
	
	/* Method to read plans.csv file and map the rate to the corresponding rate areas */
	protected static void populateRateAreaToRatesMap() throws FileNotFoundException, IOException {
		int lineCount = 1;
		try (BufferedReader br = new BufferedReader(new FileReader("plans.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lineCount != 1) {
					String[] row = line.split(",");
				    String rateArea = row[4];
					Set<Double> rates = RATEAREA_TO_RATES.getOrDefault(rateArea, new TreeSet<>());
					if ("Silver".equalsIgnoreCase(row[2])) {
						rates.add(Double.valueOf(row[3]));
					}
					RATEAREA_TO_RATES.put(rateArea, rates);
				}
				lineCount++;
			}
		}
	}
	
	/* Method to calculate the rates for a given zip code and find the second lowest */
	private static void calculateSLCSP() throws FileNotFoundException, IOException {
		int lineCount = 1;
		try (BufferedReader br = new BufferedReader(new FileReader("slcsp.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lineCount != 1) {
					String[] row = line.split(",");
					Integer zipCode = Integer.valueOf(row[0]);
					Set<String> rateAreas = ZIPCODE_TO_RATEAREA.get(zipCode);
					System.out.print(zipCode);
					System.out.print(",");
					if (rateAreas == null || rateAreas.size() != 1) {
						System.out.println();
					} else {
						Set<Double> rates = RATEAREA_TO_RATES.get(rateAreas.iterator().next());
						if (rates == null || rates.size() <= 1) {
							System.out.println();
						} else {
							Iterator<Double> ratesIterator =  rates.iterator();
							ratesIterator.next();
							System.out.println(String.format("%.2f", ratesIterator.next()));
						}
					}
				} else {
					System.out.println(line);
				}
				lineCount++;
			}
		}
	}

}
