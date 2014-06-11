package james.toy.rain1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;


public class RainParser {
	public ArrayList<RainData> rainDatas;
	public Map<String, RainData> rainDataMap;
	
	public String exceptionMsg = "";
	
	public RainParser(AsyncParser task) {
		Parser(task);
	}
	
	public void Parser(AsyncParser task) {
		rainDatas = new ArrayList<RainData>();
		rainDataMap = new HashMap<String, RainData>();
		
		Document doc;
		try {
			task.onMyProgressUpdate(10);
			
			doc = Jsoup.connect("http://www.cwb.gov.tw/V7/observe/rainfall/A136.htm").get();
			task.onMyProgressUpdate(20);
			
			Element content = doc.getElementById("tableData");
			task.onMyProgressUpdate(40);
			
			Elements table = content.select("tr");			
			task.onMyProgressUpdate(60);
			
			for (Element row : table)
			{
				Elements cells = row.select("td");
				
				if (cells.size() == 11)
				{
					RainData rd = new RainData();
					rd.region = cells.get(0).text();
					rd.station = cells.get(1).text();
					rd.tenMinute = GetRainValue(cells.get(2).text());
					rd.oneHour = GetRainValue(cells.get(3).text());
					rd.threeHour = GetRainValue(cells.get(4).text());
					rd.sixHour = GetRainValue(cells.get(5).text());
					rd.twelveHour = GetRainValue(cells.get(6).text());
					rd.twentyFourHour = GetRainValue(cells.get(7).text());
					rd.today = GetRainValue(cells.get(8).text());
					rd.yesterday = GetRainValue(cells.get(9).text());
					rd.twoDays = GetRainValue(cells.get(10).text());
					
					rainDatas.add(rd);
					rainDataMap.put(rd.station, rd);
				}
			}
			
			task.onMyProgressUpdate(100);
		} catch (Exception e) {
			exceptionMsg = e.getMessage();
		}
	}
	
	public static double GetRainValue(String s)
	{
		double ret = 0;
		
		if (s.equals("-")) {
			return 0.0;
		}
		else if (s.equals("X")) {
			return 0.0;
		}
		else {
			try {
				ret = Double.parseDouble(s);
			} catch (NumberFormatException e) {			
			}
			
			return ret;
		}
	}
}
