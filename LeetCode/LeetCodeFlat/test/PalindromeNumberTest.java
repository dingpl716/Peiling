import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PalindromeNumberTest {

	PalindromeNumber pn = new PalindromeNumber();
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
//	public void test1() {
//		assertEquals(true, pn.isPalindrome(0));
//	}
//	
//	@Test
//	public void test2() {
//		assertEquals(true, pn.isPalindrome(5));
//	}
//	
//	@Test
//	public void test3() {
//		assertEquals(true, pn.isPalindrome(-5));
//	}
//	
//	@Test
//	public void test4() {
//		assertEquals(true, pn.isPalindrome(-353));
//	}
//	
//	@Test
//	public void test5() {
//		assertEquals(true, pn.isPalindrome(-33));
//	}
//	
//	@Test
//	public void test6() {
//		assertEquals(true, pn.isPalindrome(123321));
//	}
//	
//	@Test
//	public void test7() {
//		assertEquals(true, pn.isPalindrome(1234321));
//	}
//
//	@Test
//	public void test8() {
//		assertEquals(false, pn.isPalindrome(12));
//	}
//	
//	@Test
//	public void test9() {
//		assertEquals(false, pn.isPalindrome(123));
//	}
//	
//	@Test
//	public void test10() {
//		assertEquals(false, pn.isPalindrome(1231));
//	}
	
//	@Test
	public void test11() {
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(-1 * Integer.MIN_VALUE);
//		System.out.println(Integer.MIN_VALUE + 1);
//		System.out.println(Integer.MIN_VALUE - 1);
		assertEquals(false, pn.isPalindrome(Integer.MIN_VALUE));
	}
	
	@Test
	public void test12() {
		assertEquals(false, pn.isPalindrome(-10));
	}
}
