import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MinimumWindowSubstringTest {

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
		MinimumWindowSubstring mws = new MinimumWindowSubstring();
		String s = "bdab";
		String t = "ab";
		System.out.println(mws.minWindow(s, t));
	}

}
