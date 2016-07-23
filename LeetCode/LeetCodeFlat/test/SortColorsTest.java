import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SortColorsTest {

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
		SortColors sc = new SortColors();
		int[] data = {1,1};
		sc.sortColors(data);
		for(int d: data)
			System.out.print(d + " ");
		System.out.println();
	}

	@Test
	public void test1() {
		SortColors sc = new SortColors();
		int[] data = {1,0,2};
		sc.sortColors(data);
		for(int d: data)
			System.out.print(d + " ");
		System.out.println();
	}
	
	@Test
	public void test2() {
		SortColors sc = new SortColors();
		int[] data = {2,1};
		sc.sortColors(data);
		for(int d: data)
			System.out.print(d + " ");
		System.out.println();
	}
}
