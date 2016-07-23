import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ReverseWordsInAStringTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ReverseWordsInAString rs = new ReverseWordsInAString();
		String s = "abv";
		String[] tokens1 = s.split("  ");
		for(int i=0; i<tokens1.length; ++i)
			System.out.print("'" + tokens1[i] + "'" + "\t");
		System.out.println();
		
		ArrayList<String> tokens2 = rs.split(s, "  ");
		for(String str : tokens2)
			System.out.print("'" + str + "'" + "\t");
		System.out.println();
	}

}
