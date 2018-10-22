package technical;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.junit.Test;

public class EmaTest {

	@Test
	public void testEma() {
		
		
		// https://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:moving_averages
		
		BigDecimal a = (new BigDecimal("90.99")); // 90.99, scale 2
		BigDecimal b = (new BigDecimal(90.99));   // 90.9899999999999948840923025272786617279052734375, scale 46
		
		// input
		ArrayList<BigDecimal> in = new ArrayList<BigDecimal>();
		in.add(new BigDecimal("22.27"));
		in.add(new BigDecimal("22.19"));
		in.add(new BigDecimal("22.08"));
		in.add(new BigDecimal("22.17"));
		in.add(new BigDecimal("22.18"));
		in.add(new BigDecimal("22.13"));
		in.add(new BigDecimal("22.23"));
		in.add(new BigDecimal("22.43"));
		in.add(new BigDecimal("22.24"));
		in.add(new BigDecimal("22.29"));
		in.add(new BigDecimal("22.15"));
		in.add(new BigDecimal("22.39"));
		in.add(new BigDecimal("22.38"));
		in.add(new BigDecimal("22.61"));
		in.add(new BigDecimal("23.36"));
		in.add(new BigDecimal("24.05"));
		in.add(new BigDecimal("23.75"));
		in.add(new BigDecimal("23.83"));
		in.add(new BigDecimal("23.95"));
		in.add(new BigDecimal("23.63"));
		in.add(new BigDecimal("23.82"));
		in.add(new BigDecimal("23.87"));
		in.add(new BigDecimal("23.65"));
		in.add(new BigDecimal("23.19"));
		in.add(new BigDecimal("23.10"));
		in.add(new BigDecimal("23.33"));
		in.add(new BigDecimal("22.68"));
		in.add(new BigDecimal("23.10"));
		in.add(new BigDecimal("22.40"));
		in.add(new BigDecimal("22.17"));
		
		// expected (scaled to two decimal places)
		ArrayList<BigDecimal> expected = new ArrayList<BigDecimal>();
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("0").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.22").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.21").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.24").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.27").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.33").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.52").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.80").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.97").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.13").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.28").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.34").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.43").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.51").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.53").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.47").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.40").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.39").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.26").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.23").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("23.08").setScale(2, RoundingMode.HALF_UP));
		expected.add(new BigDecimal("22.92").setScale(2, RoundingMode.HALF_UP));

		// actual
		Ema actual = new Ema(in, 10, 2);

		assertEquals(expected, actual.getEma());

	}

}
