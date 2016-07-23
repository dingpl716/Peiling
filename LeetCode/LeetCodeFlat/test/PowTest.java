import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PowTest {

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
		Pow p = new Pow();
		System.out.println(p.pow(34.0115d, -3));
	}
	
//	@Test
	public void test2() {
		Pow p = new Pow();
		System.out.println(p.pow(8.88023d, 3));
	}

	@Test
	public void test3() {
		Pow p = new Pow();
		System.out.println(p.pow(8.84372d, -5));
	}
}
