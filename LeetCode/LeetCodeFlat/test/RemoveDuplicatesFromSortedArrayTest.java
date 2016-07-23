import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RemoveDuplicatesFromSortedArrayTest {

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
		RemoveDuplicatesFromSortedArray r = new RemoveDuplicatesFromSortedArray();
		System.out.println(r.removeDuplicates(new int[]{1,2,2}));
	}

}
