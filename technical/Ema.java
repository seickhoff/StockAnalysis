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
	public Ema() { 
		this.period = 50;
		this.precision = 2;
		this.data = new ArrayList<BigDecimal>();
	}

	// calculate SMA
	public void calculateEma() {
		
		/*
		ArrayList<BigDecimal> ema = new ArrayList<BigDecimal>();
		ArrayDeque<BigDecimal> window = new ArrayDeque<BigDecimal>();
		
		BigDecimal periodBigDec = new BigDecimal(this.period);
		
		BigDecimal k = new BigDecimal((2.0 / (this.period + 1)));
		
		int count = 0;
		
		BigDecimal previousEma;
		BigDecimal scaled;
		
		for (BigDecimal value : this.data) {
			
			count++;
			
			
			
			if (count < this.period) {
							
				window.add(value);
				
				// get ema
				BigDecimal sum = new BigDecimal(0);
				
				for (BigDecimal i : window) {
					sum = sum.add(i);
				}
				BigDecimal avg = sum.divide(periodBigDec, RoundingMode.HALF_UP);
				scaled = avg.setScale(this.precision, RoundingMode.HALF_UP); // scaled to two decimal places
				
				ema.add(scaled); 
				
				//window.removeFirst();
			}
			else {
				BigDecimal ma = value.subtract(previousEma);
				ema.add(new BigDecimal("0.00").setScale(this.precision, RoundingMode.HALF_UP));
			}
			
			previousEma = scaled;
		}
		
		*/
		
		this.ema = ema;
	}

	
}
