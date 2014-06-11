package james.toy.rain1;

public class RainData {
	public String region;
	public String station;
	
	public double tenMinute;
	public double oneHour;
	public double threeHour;
	public double sixHour;
	public double twelveHour;
	public double twentyFourHour;
	public double today;
	public double yesterday;
	public double twoDays;
	
	public String GetDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("10分鐘: ");
		sb.append(tenMinute);
		sb.append("\n");

		sb.append("1小時: ");
		sb.append(oneHour);
		sb.append("\n");

		sb.append("3小時: ");
		sb.append(threeHour);
		sb.append("\n");
	
		sb.append("6小時: ");
		sb.append(sixHour);
		sb.append("\n");
	
		sb.append("12小時: ");
		sb.append(twelveHour);
		sb.append("\n");
	
		sb.append("24小時: ");
		sb.append(twentyFourHour);
		sb.append("\n");
		
		sb.append("本日: ");
		sb.append(today);
		sb.append("\n");
		
		sb.append("前一日: ");
		sb.append(yesterday);
		sb.append("\n");
		
		sb.append("前二日: ");
		sb.append(twoDays);
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String GetStatus() {
		if (tenMinute != 0) {
			return "下雨中，10分鐘雨量: " + tenMinute;
		}
		else if (oneHour != 0) {
			return "剛下雨，1小時雨量: " + oneHour;
		}
		else if (threeHour != 0) {
			return "雨停1個多小時了";
		}
		else if (sixHour != 0) {
			return "雨停3個多小時了";
		} else if (twelveHour != 0) {
			return "雨停6個多小時了";
		} else if (twentyFourHour != 0) {
			return "雨停半天以上了";
		} else {
			return "一整天都沒下雨";
		}
	}
}
