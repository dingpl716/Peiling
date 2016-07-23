import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GenerateParenthesisTest {

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
		GenerateParenthesis gp = new GenerateParenthesis();
		ArrayList<String> result = gp.generateParenthesis(2);
		for(String str : result){
			System.out.println(str);
		}
	}

}
