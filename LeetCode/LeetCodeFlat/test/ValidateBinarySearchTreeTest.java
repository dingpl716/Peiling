import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.TreeNode;


public class ValidateBinarySearchTreeTest {

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
		TreeNode t10 = new TreeNode(10);
		TreeNode t5 = new TreeNode(5);
		TreeNode t15 = new TreeNode(15);
		TreeNode t6 = new TreeNode(6);
		TreeNode t20 = new TreeNode(20);
		t10.left = t5;
		t10.right = t15;
		t15.left = t6;
		t15.right = t20;
		
		ValidateBinarySearchTree v = new ValidateBinarySearchTree();
		v.isValidBST(t10);
	}

}
