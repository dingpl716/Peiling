import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.TreeNode;


public class FlattenBinaryTreeToLinkedListTest {

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
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		t1.left = t2;
		t2.left = t3;
		t2.right = t4;
		t1.right = t5;
		t5.right = t6;
		
		FlattenBinaryTreeToLinkedList fb = new FlattenBinaryTreeToLinkedList();
		fb.flatten(t1);
		
		System.out.println();
	}

}
