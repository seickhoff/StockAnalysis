package technical;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * SMA - Simple Moving Average
 * 
 *
 * @author Scott Eickhoff
 */
public class Sma {

	
	private ArrayList<BigDecimal> data;  // source data
	
	/** source data */
	private int period;
	
	
	private int precision;
	
	/** calculated SMA data */
	private ArrayList<BigDecimal> sma;

	/**
	 * @param data set the array of data
	 */
	public void setData(ArrayList<BigDecimal> data) {
		this.data = data;
	}

	/**
	 * @param period set the time period
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * @param precision set the precision after decimal place
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	/**
	 * @return data get the SMA array
	 */
	public ArrayList<BigDecimal> getSma() {
		calculateSma();
		return sma;
	}
	
	/**
	* Constructor for class Sma
	* 
	* @param data array of data to calculate SMA
	* @param period time period for the calculation
	* @param precision precision after decimal place for the calculated data
	*/
	public Sma(ArrayList<BigDecimal> data, int period, int precision) { 
		this.data = data;
		this.period = period;
		this.precision = precision;
	}

    /**
     * Creates the SMA array and places into sma
     */
	private void calculateSma() {
		
		// will hold the calculated averages
		ArrayList<BigDecimal> sma = new ArrayList<BigDecimal>();
		
		// holds the 
		ArrayDeque<BigDecimal> window = new ArrayDeque<BigDecimal>();
		
		BigDecimal periodBigDec = new BigDecimal(this.period);
		
		int count = 0;
		
		// iterate over the data records
		for (BigDecimal value : this.data) {
			
			count++;
			
			// insert the value at the end of the deque
			window.add(value);
			
			// we have enough records match the period 
			if (count >= this.period) {
							
				// sum of all records in window
				BigDecimal sum = new BigDecimal(0);
				for (BigDecimal i : window) {
					sum = sum.add(i);
				}
				
				// get average and set precision
				BigDecimal avg = sum.divide(periodBigDec, RoundingMode.HALF_UP);
				BigDecimal scaled = avg.setScale(this.precision, RoundingMode.HALF_UP);
				
				sma.add(scaled); 
				
				// roll off first record
				window.removeFirst();
			}
			// pad with zeros until we have enough records as the period
			else {
				sma.add(new BigDecimal("0.00").setScale(this.precision, RoundingMode.HALF_UP));
			}
		}
		
		this.sma = sma;
	}

	
}
