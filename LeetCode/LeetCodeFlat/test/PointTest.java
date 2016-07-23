import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import definition.Point;


public class PointTest {

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
		Point p1 = new Point(2, 3);
		Point p2 = new Point(3, 3);
		Point p3 = new Point(-5, 3);
		Point points[] = {p1, p2, p3};
		
		MaxPointsonaLine m = new MaxPointsonaLine();
		int res = m.maxPoints(points);
		System.out.println(res);
		
	}

}
