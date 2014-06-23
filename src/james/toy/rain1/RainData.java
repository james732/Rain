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
		sb.append("10����: ");
		sb.append(rainToString(tenMinute));
		sb.append("\n");

		sb.append("1�p��: ");
		sb.append(rainToString(oneHour));
		sb.append("\n");

		sb.append("3�p��: ");
		sb.append(rainToString(threeHour));
		sb.append("\n");
	
		sb.append("6�p��: ");
		sb.append(rainToString(sixHour));
		sb.append("\n");
	
		sb.append("12�p��: ");
		sb.append(rainToString(twelveHour));
		sb.append("\n");
	
		sb.append("24�p��: ");
		sb.append(rainToString(twentyFourHour));
		sb.append("\n");
		
		sb.append("����: ");
		sb.append(rainToString(today));
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String getStatus() {		
		if ((tenMinute < 0) && (oneHour < 0) && (threeHour < 0)) {
			return "�L���p�ɤ����";
		}
		else if (tenMinute > 0) {
			return "�U�B���A�Q�����B�q: " + tenMinute;
		} else if (oneHour > 0) {
			return "��U�B�A�@�p�ɫB�q: " + oneHour;
		} else if (threeHour > 0) {
			return "�B���@�Ӧh�p�ɤF";
		} else if (sixHour > 0) {
			return "�B���T�Ӥp�ɥH�W�F";
		} else if (twelveHour > 0) {
			return "�B�����Ӥp�ɥH�W�F";
		} else if (twentyFourHour != 0) {
			return "�B���b�ѥH�W�F";
		} else {
			return "�@��ѳ��S�U�B";
		}
	}
}