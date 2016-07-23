package string_array;
//	Given two binary strings, return their sum (also a binary string).
//	
//	For example,
//	a = "11"
//	b = "1"
//	Return "100".

public class AddBinary {
    
	public String addBinary(String a, String b) {
		if((a == null || a.length() == 0) && (b == null || b.length() == 0))
			return "";
		else if(a == null || a.length() == 0)
			return b;
		else if(b == null || b.length() == 0)
			return a;
		else {
			StringBuffer sb = new StringBuffer();
			if(a.length() < b.length()) {  // a 一定比b长或者相等
				String tmp = a;
				a = b;
				b = tmp;
			}
			a = reverse(a);
			b = reverse(b);
			int carrier = 0;
			// 在这里直接遍历完最长数组，每次遍历的时候检查下是否超出短的数组了，
			// 如果超出了就赋0，这样代码很简洁
			for(int i=0; i<a.length(); ++i) {
				int digita = a.charAt(i)-'0';
				int digitb = i < b.length() ? b.charAt(i) - '0' : 0;
				int val = (digita + digitb + carrier)%2;
				sb.append(val);
				carrier = (digita + digitb + carrier)/2;
			}
			if(carrier == 1) {
				sb.append(carrier);
			}
			return reverse(sb.toString());
		}
    }
	
	private String reverse(String s) {
		StringBuffer sb = new StringBuffer();
		for(int i=s.length()-1; i>=0; --i)
			sb.append(s.charAt(i));
		return sb.toString();
	}
}
