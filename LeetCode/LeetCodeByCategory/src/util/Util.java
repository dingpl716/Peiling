package util;

import java.util.List;

public class Util {
	
	public static void PrintListOfString(List<String> strings){
		
		for(int i = 0; i<strings.size(); ++i){
			System.out.println(strings.get(i));
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
}
