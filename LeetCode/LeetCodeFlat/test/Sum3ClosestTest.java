import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class Sum3ClosestTest {

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
		int[] num = {1,1,1,0};
		Sum3Closest s3 = new Sum3Closest();
		System.out.println(s3.threeSumClosest(num, 100));
	}

	@Test
	public void test1() {
		int[] num = {4,0,5,-5,3,3,0,-4,-5};
		Sum3Closest s3 = new Sum3Closest();
		System.out.println(s3.threeSumClosest(num, -2));
	}
}
