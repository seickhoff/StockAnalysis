package technical;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Sma {

	private ArrayList<BigDecimal> data;
	private int period;
	private int precision;
	private ArrayList<BigDecimal> sma;

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
	public ArrayList<BigDecimal> getSma() {
		calculateSma();
		return sma;
	}
	
	// constructor
	public Sma() { 
		this.period = 50;
		this.precision = 2;
		this.data = new ArrayList<BigDecimal>();
	}

	// calculate SMA
	public void calculateSma() {
		
		ArrayList<BigDecimal> sma = new ArrayList<BigDecimal>();
		ArrayDeque<BigDecimal> window = new ArrayDeque<BigDecimal>();
		
		BigDecimal periodBigDec = new BigDecimal(this.period);
		
		int count = 0;
		
		for (BigDecimal value : this.data) {
			
			count++;
			
			window.add(value);
			
			if (count >= this.period) {
							
				// get sma
				BigDecimal sum = new BigDecimal(0);
				
				for (BigDecimal i : window) {
					sum = sum.add(i);
				}
				BigDecimal avg = sum.divide(periodBigDec, RoundingMode.HALF_UP);
				BigDecimal scaled = avg.setScale(this.precision, RoundingMode.HALF_UP); // scaled to two decimal places
				
				sma.add(scaled); 
				
				window.removeFirst();
			}
			else {
				sma.add(new BigDecimal("0.00").setScale(this.precision, RoundingMode.HALF_UP));
			}
		}
		
		this.sma = sma;
	}

	
}
