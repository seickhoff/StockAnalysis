package candlestick;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CandleStickData {

	public BigDecimal open;
	public BigDecimal close;
	public BigDecimal high;
	public BigDecimal low;
	public long volume;
	public Date day;
	
	public CandleStickData(Date day, BigDecimal open, BigDecimal close, BigDecimal high, BigDecimal low, long volume) {
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.day = day;
		this.volume = volume;
	}
	
	public void print() {
		/*
		System.out.println(
			"Day: " + this.day.toString() + ", " +
			"Open: " + NumberFormat.getCurrencyInstance().format(this.open) + ", " +
			"Close: " + NumberFormat.getCurrencyInstance().format(this.close) + ", " +
			"High: " + NumberFormat.getCurrencyInstance().format(this.high) + ", " +
			"Low: " + NumberFormat.getCurrencyInstance().format(this.low)
		);
		*/
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		String date = formatDate.format( this.day );
		
		DecimalFormat df = new DecimalFormat("#.00"); 
		
		System.out.println(
			"Day: " + date + ", " +
			"Open: " + df.format(this.open) + ", " +
			"Close: " + df.format(this.close) + ", " +
			"High: " + df.format(this.high) + ", " +
			"Low: " + df.format(this.low) + ", " +
			"Vol: " + this.volume 
		);
		
	}
	
}
