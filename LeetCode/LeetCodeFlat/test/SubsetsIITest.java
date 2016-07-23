import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SubsetsIITest {

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
		SubsetsII ss = new SubsetsII();
		int[] a = {1, 2, 2};
		ArrayList<ArrayList<Integer>> result = ss.subsetsWithDup(a);
		System.out.println("number of subsets: " + result.size());
		for(int i=0; i<result.size(); ++i) {
			ArrayList<Integer> subset = result.get(i);
			for(int j=0; j<subset.size(); ++j) {
				System.out.print(subset.get(j) + " ");
			}
			System.out.println();
//			System.out.println("=============================");
		}
	}

}
