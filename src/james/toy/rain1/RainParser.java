package james.toy.rain1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RainParser {
	public ArrayList<RainData> rainDatas;
	public Map<String, RainData> rainDataMap;
	
	public String exceptionMsg = "";
	
	public RainParser() {
		Parser();
	}
	
	public void Parser() {
		rainDatas = new ArrayList<RainData>();
		rainDataMap = new HashMap<String, RainData>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("http://opendata.cwb.gov.tw/opendata/DIV2/O-A0002-001.xml");
			Element root = document.getDocumentElement();
			NodeList nodes = root.getElementsByTagName("location");
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Element e = (Element)nodes.item(i);
				RainData rd = new RainData();
				rd.station = e.getElementsByTagName("locationName").item(0).getTextContent();
				
				NodeList weatherNodes = e.getElementsByTagName("weatherElement");
				
				for (int j = 0; j < weatherNodes.getLength(); j++) {
					Element weather = (Element)weatherNodes.item(j);
					
					String elementString = weather.getElementsByTagName("elementName").item(0).getTextContent();
					double elementValue  = Double.parseDouble(weather.getElementsByTagName("value").item(0).getTextContent());
					
					if (elementString.equals("RAIN")) {
						rd.oneHour = elementValue;
					}
					else if (elementString.equals("MIN_10")) {
						rd.tenMinute = elementValue;
					}
					else if (elementString.equals("HOUR_3")) {
						rd.threeHour = elementValue;
					}
					else if (elementString.equals("HOUR_6")) {
						rd.sixHour = elementValue;
					}
					else if (elementString.equals("HOUR_12")) {
						rd.twelveHour = elementValue;
					}
					else if (elementString.equals("HOUR_24")) {
						rd.twentyFourHour = elementValue;
					}
					else if (elementString.equals("NOW")) {
						rd.today = elementValue;
					}
					else {
						
					}
				} // weatherElement
				
				NodeList paramList = e.getElementsByTagName("parameter");
				for (int j = 0; j < paramList.getLength(); j++) {
					Element paramElement = (Element)paramList.item(j);
					
					String parameterName = paramElement.getElementsByTagName("parameterName").item(0).getTextContent();
					String parameterValue = paramElement.getElementsByTagName("parameterValue").item(0).getTextContent();

					if (parameterName.equals("CITY") || parameterName.equals("TOWN")) {
						rd.region += parameterValue;
					}
				} // parameter
				
				rainDatas.add(rd);
				rainDataMap.put(rd.station, rd);
			}
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
