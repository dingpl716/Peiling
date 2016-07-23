import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.ListNode;
import definition.TreeNode;


public class SortedListToBalancedBSTTest {

	SortedListToBalancedBST s = new SortedListToBalancedBST();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		
		
		TreeNode tree = s.sortedListToBST(l1);
		System.out.println(tree.val);
	}
	
	@Test
	public void test1() {
		int[] array = {1,2,3,4,5};
		ListNode head = ListNode.arrayToList(array);
		TreeNode tree = s.sortedListToBST(head);
		System.out.println(tree.val);
	}

}
