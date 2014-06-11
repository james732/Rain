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
		sb.append("10����: ");
		sb.append(tenMinute);
		sb.append("\n");

		sb.append("1�p��: ");
		sb.append(oneHour);
		sb.append("\n");

		sb.append("3�p��: ");
		sb.append(threeHour);
		sb.append("\n");
	
		sb.append("6�p��: ");
		sb.append(sixHour);
		sb.append("\n");
	
		sb.append("12�p��: ");
		sb.append(twelveHour);
		sb.append("\n");
	
		sb.append("24�p��: ");
		sb.append(twentyFourHour);
		sb.append("\n");
		
		sb.append("����: ");
		sb.append(today);
		sb.append("\n");
		
		sb.append("�e�@��: ");
		sb.append(yesterday);
		sb.append("\n");
		
		sb.append("�e�G��: ");
		sb.append(twoDays);
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String GetStatus() {
		if (tenMinute != 0) {
			return "�U�B���A10�����B�q: " + tenMinute;
		}
		else if (oneHour != 0) {
			return "��U�B�A1�p�ɫB�q: " + oneHour;
		}
		else if (threeHour != 0) {
			return "�B��1�Ӧh�p�ɤF";
		}
		else if (sixHour != 0) {
			return "�B��3�Ӧh�p�ɤF";
		} else if (twelveHour != 0) {
			return "�B��6�Ӧh�p�ɤF";
		} else if (twentyFourHour != 0) {
			return "�B���b�ѥH�W�F";
		} else {
			return "�@��ѳ��S�U�B";
		}
	}
}
