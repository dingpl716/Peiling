import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SearchMatrixTest {
	
	static int[][] matrix;
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
		SearchMatrix sm = new SearchMatrix();
		matrix = new int[][] {{0,1,2,3,4}, {5,6,7,8,9}};
		int[] targets = new int[] {0, 4, 5, 9};
		for(int target : targets)
			System.out.println(sm.searchMatrix2(matrix, target));
		
		System.out.println(sm.searchMatrix(matrix, 100));
	}
	
	@Test
	public void test2() {
		SearchMatrix sm = new SearchMatrix();
		matrix = new int[][] {{0,1,2,3,4}, {10,11,12,13,14}};
		int[] targets = new int[] {5, 6, 7, 8, 9};
		for(int target : targets)
			System.out.println(sm.searchMatrix2(matrix, target));
		
		System.out.println(sm.searchMatrix(matrix, 0));
		System.out.println(sm.searchMatrix(matrix, 10));
	}

}
