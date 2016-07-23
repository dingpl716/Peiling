import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SubsetsTest {

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
		Subsets s = new Subsets();
		int[] n = {1,2,3};
		ArrayList<ArrayList<Integer>> result = s.subsets(n);
		for(ArrayList<Integer> list : result) {
			for(Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}
