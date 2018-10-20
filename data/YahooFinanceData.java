package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import candlestick.CandleStickData;

public class YahooFinanceData {

	public String symbol;
	public String startDate;
	public String endDate;
	public ArrayList<CandleStickData> data = new ArrayList<CandleStickData>();
	
	private String cookie;
	private String crumb;
	private String urlCookie = "https://finance.yahoo.com";
	private String urlCrumb = "https://query1.finance.yahoo.com/v1/test/getcrumb";
	private String urlData = "https://query1.finance.yahoo.com/v7/finance/download/";
	
	// constructor
	public YahooFinanceData(String symbol, String startDate, String endDate)  {

		getCookie();
		getCrumb();
		
		this.symbol = symbol;
		this.startDate = startDate;
		this.endDate = endDate;
		
		DateFormat dfm = new SimpleDateFormat("yyyyMMdd");
		
		long a = 0;
		long b = 0;
		try {
			a = dfm.parse(startDate).getTime() / 1000;
			b = dfm.parse(endDate).getTime() / 1000;
		} 
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			String endpoint = this.urlData + this.symbol + "?" +
				"period1=" + a + "&" +
				"period2=" + b + "&interval=1d&events=history&crumb=" + this.crumb;
			
			// get URL content
			URL url = new URL(endpoint);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", this.cookie); 

			// line of CSV data
			String inputLine;
			
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			// line count
			int line = 0;
			
			// process each line
			while ((inputLine = br.readLine()) != null) {
				
				//System.out.println("[" + inputLine + "]");
				
				// skip header
				if (++line >= 2) {
					this.data.add(this.parseLineToCandleStickData(inputLine));
				}
			}
			
			// arrange series into ascending date order
			Collections.reverse(this.data);

			br.close();
			
		} 
		catch (MalformedURLException e) {
			System.err.println("MalformedURLException: " + e.getMessage());
		}
		catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}

	}
	
	/**
	 */
	private void getCrumb() {

		this.crumb = null;		
		
		try {		
			
			URL url = new URL(this.urlCrumb);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", this.cookie); 
			con.connect();
			
			
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String line = br.readLine();
			
			br.close();
			
			if (line != null) {
				this.crumb = line;
			}
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	private void getCookie() {
		
		this.cookie = null;
		int retries = 50;
		
		URL url;
		
		boolean done = false;
		
		// retries
		for (int i = 0; i < retries; i++) {
		
			try {
				
				//System.out.println("try: " + i);
				
				url = new URL(this.urlCookie);
				
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(2000);
				con.setReadTimeout(2000);
				con.setRequestMethod("HEAD");
				String cookiesHeader = con.getHeaderField("Set-Cookie");

				
				if (cookiesHeader != null) {
					System.out.println(cookiesHeader);
					
					Pattern pattern = Pattern.compile("(B=.*?);");
					Matcher matcher = pattern.matcher(cookiesHeader);
					
	
			        while(matcher.find()) {
			        	System.out.println("found: " + matcher.group(1));
			        	done = true;
			        	this.cookie = matcher.group(1);
			        }
				}
				
				if (done) {
					break;
				}
				
			} 
			catch (Exception e) {
				System.out.println("Exception: " + e.getMessage());
			}
		
		}
	}

	// iterate over the list of CandleStickDay data (this.data) and print out each day
	public void print() {
		
		for (CandleStickData day : this.data) {
			day.print();
		}
	
	}
	
	// create a list of just the close data
	public ArrayList<BigDecimal> getClose() {
		
		ArrayList<BigDecimal> arrayList = new ArrayList<BigDecimal>();
		
		for (CandleStickData day : this.data) {
			
			arrayList.add(day.close);
		}		
		
		return arrayList;
	}
	

	
	private CandleStickData parseLineToCandleStickData(String inputLine) {
		
		List<String> fields = Arrays.asList(inputLine.split(","));
		
		BigDecimal open = new BigDecimal(fields.get(1));
		BigDecimal high = new BigDecimal(fields.get(2));
		BigDecimal low = new BigDecimal(fields.get(3));
		BigDecimal close = new BigDecimal(fields.get(4));
		BigDecimal adj = new BigDecimal(fields.get(5));
		long volume = Long.parseLong(fields.get(6), 10);

		
		// to factor in price splits 
		BigDecimal factor = adj.divide(close, RoundingMode.HALF_UP);
		
		SimpleDateFormat formatDateData = new SimpleDateFormat("yyyy-MM-dd");
		Date dateData = null;
		
		try {
			dateData = formatDateData.parse( fields.get(0) );
		} 
		catch (ParseException e) {
			System.err.println("ParseException: " + e.getMessage());
		}
		
		CandleStickData candleStickDay = new CandleStickData(
			dateData,
			factor.multiply(open), 
			factor.multiply(close),
			factor.multiply(high),
			factor.multiply(low),
			volume
		);
			
		return candleStickDay;
	
	}
	
}
