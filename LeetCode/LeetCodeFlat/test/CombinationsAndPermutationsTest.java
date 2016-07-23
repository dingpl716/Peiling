import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CombinationsAndPermutationsTest {

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
	public void testPermutation() {
		CombinationsAndPermutations cp = new CombinationsAndPermutations();
		ArrayList<ArrayList<Integer>> results = cp.permutate(4, 3);
		for(ArrayList<Integer> result: results) {
			for(Integer i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}
