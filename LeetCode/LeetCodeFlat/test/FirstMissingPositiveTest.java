import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FirstMissingPositiveTest {

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
		
		int[] A = {3, 4, -1, 1};
		FirstMissingPositive f = new FirstMissingPositive();
		f.firstMissingPositive(A);
		for(int a : A) {
			System.out.print(a + " ");
		}
		System.out.println("========================================");
	}
	
	@Test
	public void test2() {
		
		int[] A = {3, 5, -1, 1};
		FirstMissingPositive f = new FirstMissingPositive();
		f.firstMissingPositive(A);
		for(int a : A) {
			System.out.print(a + " ");
		}
		System.out.println("========================================");
	}

	@Test
	public void test3() {
		
		int[] A = {3, 5, 4, 2};
		FirstMissingPositive f = new FirstMissingPositive();
		f.firstMissingPositive(A);
		for(int a : A) {
			System.out.print(a + " ");
		}
		System.out.println("========================================");
	}
}
