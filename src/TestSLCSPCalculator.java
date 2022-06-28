import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSLCSPCalculator {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	public void testHeaderOutput() throws FileNotFoundException, IOException {
		SLCSPCalculator.main(null);
		String[] headers = outContent.toString().split("\n")[0].split(",");
		assertNotNull(headers);
		assertEquals(2, headers.length);
		assertEquals("zipcode", headers[0]);
		assertEquals("rate", headers[1].substring(0, headers[1].length() - 1));
	}
	
	@Test
	public void testFirstOutput() throws FileNotFoundException, IOException {
		SLCSPCalculator.main(null);
		String[] firstZipCode = outContent.toString().split("\n")[1].split(",");
		assertNotNull(firstZipCode);
		assertEquals(2, firstZipCode.length);
		assertEquals("64148", firstZipCode[0]);
		assertEquals("195.66", firstZipCode[1].substring(0, firstZipCode[1].length() - 1));
	}
	
	@Test
	public void testBlankSLCSP() throws FileNotFoundException, IOException {
		SLCSPCalculator.main(null);
		String[] blankZipCode = outContent.toString().split("\n")[7].split(",");
		assertNotNull(blankZipCode);
		assertEquals(2, blankZipCode.length);
		assertEquals("54923", blankZipCode[0]);
		assertEquals("", blankZipCode[1].substring(0, blankZipCode[1].length() - 1));
	}
	
	@Test
	public void testOnlySilverRatesAreRead() throws FileNotFoundException, IOException {
		SLCSPCalculator.populateRateAreaToRatesMap();
		assertNotNull(SLCSPCalculator.RATEAREA_TO_RATES);
		assertFalse(SLCSPCalculator.RATEAREA_TO_RATES.isEmpty());
		Set<Double> rates = SLCSPCalculator.RATEAREA_TO_RATES.get("2");
		assertNotNull(rates);
		assertFalse(rates.isEmpty());
		// contains silver rate
		assertTrue(rates.contains(313.69));
		// does not contain gold rate
		assertFalse(rates.contains(379.97));
	}

}
