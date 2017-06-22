package util;

import java.util.List;
import java.util.function.Consumer;

public class Util {
	
	public static void PrintListOfString(List<String> strings){
		
		for(int i = 0; i<strings.size(); ++i){
			System.out.println(strings.get(i));
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}
	
	public static void timmer(Consumer consumer){
		long startTime = System.currentTimeMillis();
		consumer.accept(null);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + "ms");
	}
}