import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class WildcardMatchingTest {

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
	public void test1() {
		WildcardMatching w = new WildcardMatching();
		System.out.println(w.isMatch("aaababc", "a*bc"));
	}
	
	@Test
	public void test2() {
		WildcardMatching w = new WildcardMatching();
		System.out.println(w.isMatch("aaababc", "a*b*c"));
	}
	
	@Test
	public void test3() {
		WildcardMatching w = new WildcardMatching();
		System.out.println(w.isMatch("aaababcc", "a*b*c"));
	}
	@Test
	public void test4() {
		WildcardMatching w = new WildcardMatching();
		System.out.println(w.isMatch("aaabcbc", "a*bc"));
	}

}
