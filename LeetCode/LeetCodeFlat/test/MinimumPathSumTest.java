import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MinimumPathSumTest {

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
		MinimumPathSum mps = new MinimumPathSum();
		int[][] data = {{1, 2,}, {5, 6}, {1, 1}};
		System.out.println(mps.minPathSum2(data));
		
	}

}
