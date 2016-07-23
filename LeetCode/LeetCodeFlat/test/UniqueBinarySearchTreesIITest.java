import static org.junit.Assert.*;

import java.util.ArrayList;

import definition.TreeNode;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class UniqueBinarySearchTreesIITest {

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
		UniqueBinarySearchTreesII u = new UniqueBinarySearchTreesII();
		ArrayList<definition.TreeNode> res = u.generateTrees(4);
		System.out.println(res.size());
		
	}

}
