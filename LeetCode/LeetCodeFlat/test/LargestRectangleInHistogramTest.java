import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class LargestRectangleInHistogramTest {

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
		LargestRectangleInHistogram l = new LargestRectangleInHistogram();
		int[] height = {1,1};
		System.out.println(l.largestRectangleArea(height));
	}
	
//	@Test
	public void test2() {
		LargestRectangleInHistogram l = new LargestRectangleInHistogram();
		int[] height = {1};
		System.out.println(l.largestRectangleArea(height));
	}
	
	@Test
	public void test3() {
		LargestRectangleInHistogram l = new LargestRectangleInHistogram();
		int[] height = {3,2,3};
		System.out.println(l.largestRectangleArea(height));
	}

}
