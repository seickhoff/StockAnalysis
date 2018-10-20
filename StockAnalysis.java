import technical.Sma;
import technical.Ema;
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
			
		Ema ema = new Ema();
		ema.setData(y2.getClose());
		//System.out.println(ema.getEma().toString());
		
		Sma sma = new Sma();
		
		sma.setData(y2.getClose());
		sma.setPrecision(2);
		
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
	}

}
