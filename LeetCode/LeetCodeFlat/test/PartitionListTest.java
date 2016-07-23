import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.ListNode;


public class PartitionListTest {

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
		ListNode l2 = new ListNode(2);
		ListNode l1 = new ListNode(1);
		l2.next = l1;
		PartitionList pl = new PartitionList();
		ListNode head = pl.partition2(l2, 2);
		
		System.out.println();
		
	}

}
