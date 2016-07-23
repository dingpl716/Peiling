import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PushPopArrayTest {

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
		PushPopArray ppa = new PushPopArray();
		int[] a = {1,2,3};
		ArrayList<ArrayList<Integer>> result = ppa.getPushPopArray(a);
		
		System.out.println(result.size());
		for(ArrayList<Integer> list : result) {
			for(Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}
