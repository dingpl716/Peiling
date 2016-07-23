import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class WordBreakIITest {

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
		WordBreakII wb = new WordBreakII();
		String s = "aaaaaaa";
		Set<String> dict = new HashSet<String>();
		dict.add("aaaa");
		dict.add("aaa");
		wb.wordBreak(s, dict);
	}

}
