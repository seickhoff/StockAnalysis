package technical;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class SmaTest {

	@Test
	public void testSma() {
		
		
		BigDecimal a = (new BigDecimal("90.99")); // 90.99, scale 2
		BigDecimal b = (new BigDecimal(90.99));   // 90.9899999999999948840923025272786617279052734375, scale 46
		
		// input
		ArrayList<BigDecimal> in = new ArrayList<BigDecimal>();
		in.add(new BigDecimal("100"));
		in.add(new BigDecimal("90.99"));
		in.add(new BigDecimal("80.533"));
		in.add(new BigDecimal("70.25"));
		
		// expected (scaled to two decimal places)
		ArrayList<BigDecimal> expected = new ArrayList<BigDecimal>();
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("95.50").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("85.76").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("75.39").setScale(2, RoundingMode.HALF_UP));
		
		// actual
		Sma actual = new Sma();
		actual.setData(in);
		actual.setPeriod(2);
		actual.setPrecision(2);

		assertEquals(expected, actual.getSma());

	}

}
