package technical;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Ema {

	private ArrayList<BigDecimal> data;
	private int period;
	private int precision;
	private BigDecimal multiplier;
	private ArrayList<BigDecimal> ema;

	// setters
	public void setData(ArrayList<BigDecimal> data) {
		this.data = data;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	// getters
	public ArrayList<BigDecimal> getEma() {
		calculateEma();
		return ema;
	}
	
	// constructor
	public Ema(ArrayList<BigDecimal> data, int period, int precision) { 
		this.data = data;
		this.period = period;
		this.precision = precision;
		
		
		// calculate the factor
		BigDecimal t = new BigDecimal(2);
		BigDecimal p = new BigDecimal(period + 1);
		this.multiplier = t.divide(p, 8, RoundingMode.HALF_UP);
	}

	// calculate EMA
	public void calculateEma() {
		
		
		ArrayList<BigDecimal> ema = new ArrayList<BigDecimal>();
		ArrayDeque<BigDecimal> window = new ArrayDeque<BigDecimal>();
		
		BigDecimal previousEma = new BigDecimal(0);
		BigDecimal avg = new BigDecimal(0);
		
		
		for (BigDecimal value : this.data) {

			// prior to the period, build array; at Period calculate sma
			if (window.size() < this.period) {
				
				window.add(value);
				
				if (window.size() == this.period) {
					avg = average(window);
				}				
			}
			// post Period; just calculate ema
			else {
				BigDecimal ma = value.subtract(previousEma);	
				ma = ma.multiply(this.multiplier);
				avg = ma.add(previousEma);
				
				// internal precision set to 3 decimal places
				avg = avg.setScale(3, RoundingMode.HALF_UP);			 
			}
			
			previousEma = avg;
			
			// set precision back to 3 decimal places for final data
			avg = avg.setScale(this.precision, RoundingMode.HALF_UP);
			ema.add(avg);
		}		
		
		this.ema = ema;
	}

	
	/**
	 * Get average value of an array.
	 * 
	 * @param arr the array
	 * @return avg the average value
	 */
	private BigDecimal average(ArrayDeque<BigDecimal> arr) {
		BigDecimal sum = new BigDecimal(0);
		
		for (BigDecimal value : arr) {
			sum = sum.add(value);
		}
		
		BigDecimal size = new BigDecimal(arr.size());
		
		BigDecimal avg = new BigDecimal(0);
		avg = sum.divide(size, RoundingMode.HALF_UP);
		
		return avg;
	}

	
}
