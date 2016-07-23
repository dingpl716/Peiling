import java.util.ArrayList;


public class GrayCode {

	public ArrayList<Integer> grayCode(int n) {
		
		if(n == 0)
			return null;
		
		StringBuffer s0 = new StringBuffer("0");
		StringBuffer s1 = new StringBuffer("1");
		
		ArrayList<StringBuffer> grayCodes = new ArrayList<StringBuffer>();
		grayCodes.add(s0);
		grayCodes.add(s1);
		
		if(n == 1) {
			return generateGrayCode(grayCodes);
		}
		
        int i = 1;
		while(i < n) {
			grayCodes = getNewGrayCodes(grayCodes);
			++i;
        }
		
		return generateGrayCode(grayCodes);
    }
	
	private Integer stringToInt(String s) {
		int result = 0;
		for(int i=0; i<s.length(); ++i){
			result = result*2 + Character.getNumericValue(s.charAt(i));
		}
		
		return result;
	}
	
	private ArrayList<Integer> generateGrayCode(ArrayList<StringBuffer> list) {
		ArrayList<Integer> codes = new ArrayList<Integer>();
		
		for(int i=0; i<list.size(); ++i) {
			codes.add(stringToInt(list.get(i).toString()));
		}
		
		return codes;
	}
	
	private ArrayList<StringBuffer> getNewGrayCodes(ArrayList<StringBuffer> grayCodes) {
		int size = grayCodes.size();
		for(int i=size-1; i>=0; --i) {
			StringBuffer sb = new StringBuffer(grayCodes.get(i));  //这个必须重新new，不能直接get了之后就add进去
			grayCodes.add(sb);
		}
		
		for(int i=0; i<size; ++i) {
			grayCodes.get(i).insert(0, '0');
		}
		
		for(int i=size; i<grayCodes.size(); ++i) {
			grayCodes.get(i).insert(0, '1');
		}
		
		return grayCodes;
	}
}
