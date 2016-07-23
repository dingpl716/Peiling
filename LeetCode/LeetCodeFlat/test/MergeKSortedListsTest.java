import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.ListNode;


public class MergeKSortedListsTest {

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
		MergeKSortedLists msl = new MergeKSortedLists();
		int[][] data = {{-8,-8,-3,-2,0,2,2,3,3},
						{-5,-3,1},
						{-9,-7,-1,4},
						{-10,-4,-4,-4,0,3},
						{-2,4},
						{-9,-6,-5,-5,-3,-3,-2,2},
						{1,3},
						{-8,-3,-2,1,3}};
		ArrayList<ListNode> lists = new ArrayList<>();
		for(int[] line : data) {
			lists.add(createList(line));
		}
		msl.mergeKLists(lists);
	}
	
	private ListNode createList(int[] data) {
		ListNode head = new ListNode(data[0]);
		ListNode cur = head;
		for(int i=1; i<data.length; ++i) {
			cur.next = new ListNode(data[i]);
			cur = cur.next;
		}
		return head;
	}

}
