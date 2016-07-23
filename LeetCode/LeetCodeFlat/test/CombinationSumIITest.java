import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CombinationSumIITest {

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
	public void test() {
		CombinationSumII c = new CombinationSumII();
		c.combinationSum2(new int[]{1, 1}, 2);
	}
	
	@Test
	public void test2() {
		CombinationSumII c = new CombinationSumII();
		c.combinationSum2(new int[]{1, 1}, 1);
	}

}
