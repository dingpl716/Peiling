import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PlusOneTest {

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
		PlusOne po = new PlusOne();
		int[] data = {8,9,9,9};
		po.plusOne(data);
		
		for(int d: data)
			System.out.print(d + " ");
		System.out.println();
	}

}
