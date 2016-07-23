import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MedianOfTwoSortedArraysTest {

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
		MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
		int[] A = {3};
		int[] B = {1, 2, 4};
		System.out.println(m.findMedianSortedArrays(A, B));
	}
	
//	@Test
	public void test2() {
		MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
		int[] A = {1, 1, 1};
		int[] B = {1, 1, 1};
		System.out.println(m.findMedianSortedArrays(A, B));
	}

}
