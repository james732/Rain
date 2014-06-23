package james.toy.rain1;

class RainData {
	public String region;
	public String station;
	
	public double tenMinute;
	public double oneHour;
	public double threeHour;
	public double sixHour;
	public double twelveHour;
	public double twentyFourHour;
	public double today;
	
	String rainToString(double value) {
		if (value < 0) {
			return "No data";
		} else {
			return Double.toString(value);
		}
	}
	
	public String getDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("10分鐘: ");
		sb.append(rainToString(tenMinute));
		sb.append("\n");

		sb.append("1小時: ");
		sb.append(rainToString(oneHour));
		sb.append("\n");

		sb.append("3小時: ");
		sb.append(rainToString(threeHour));
		sb.append("\n");
	
		sb.append("6小時: ");
		sb.append(rainToString(sixHour));
		sb.append("\n");
	
		sb.append("12小時: ");
		sb.append(rainToString(twelveHour));
		sb.append("\n");
	
		sb.append("24小時: ");
		sb.append(rainToString(twentyFourHour));
		sb.append("\n");
		
		sb.append("本日: ");
		sb.append(rainToString(today));
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String getStatus() {		
		if ((tenMinute < 0) && (oneHour < 0) && (threeHour < 0)) {
			return "無六小時內資料";
		}
		else if (tenMinute > 0) {
			return "下雨中，十分鐘雨量: " + tenMinute;
		} else if (oneHour > 0) {
			return "剛下雨，一小時雨量: " + oneHour;
		} else if (threeHour > 0) {
			return "雨停一個多小時了";
		} else if (sixHour > 0) {
			return "雨停三個小時以上了";
		} else if (twelveHour > 0) {
			return "雨停六個小時以上了";
		} else if (twentyFourHour != 0) {
			return "雨停半天以上了";
		} else {
			return "一整天都沒下雨";
		}
	}
}