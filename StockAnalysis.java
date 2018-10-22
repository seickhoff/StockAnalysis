import technical.Sma;
import technical.Ema;

import java.math.BigDecimal;
import java.util.ArrayList;

import data.YahooFinanceData;

public class StockAnalysis {

	public static void main(String[] args) {


		//YahooFinanceData y1 = new YahooFinanceData("AAPL", "20160101", "20160125"); 	
		//y1.print();
		//System.out.println(y1.urlYahooApi);
		
		//YahooFinanceData y1 = new YahooFinanceData("AAPL", "20151220", "20160110");
		
		YahooFinanceData y2 = new YahooFinanceData("AAPL", "20181001", "20181019");
			
		System.out.println(y2.symbol);
		System.out.println("Data count: " + y2.data.size());
		System.out.println("startDate: " + y2.startDate);
		System.out.println("endDate: " + y2.endDate);
		y2.print();
			
		
		Sma sma = new Sma(y2.getClose(), 0, 2);		
		
		sma.setPeriod(1);
		System.out.println(sma.getSma().toString());

		sma.setPeriod(2);
		System.out.println(sma.getSma().toString());
		
		sma.setPeriod(3);
		System.out.println(sma.getSma().toString());
		
		sma.setPeriod(4);
		System.out.println(sma.getSma().toString());
		
		sma.setPeriod(5);
		System.out.println(sma.getSma().toString());
		
		
		System.out.println("EMA");
		
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
		
		Ema ema = new Ema(in, 10, 2);
	
		//System.out.println(ema.getEma().toString());
		//ema.setPeriod(5);
		System.out.println(ema.getEma().toString());
		
	}

}
