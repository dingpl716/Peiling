import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SimplifyPathTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test1() {
		SimplifyPath sp = new SimplifyPath();
		System.out.println(sp.simplifyPath("/.."));
	}
	
//	@Test
	public void test2() {
		SimplifyPath sp = new SimplifyPath();
		System.out.println(sp.simplifyPath("/..."));
	}
	
	@Test
	public void test3() {
		SimplifyPath sp = new SimplifyPath();
		System.out.println(sp.simplifyPath("/a/./b/../../c/"));
	}

}
