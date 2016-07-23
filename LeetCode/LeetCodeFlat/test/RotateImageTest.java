import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RotateImageTest {
	int[][] output;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		for(int i=0; i<output.length; ++i) {
			for(int j=0; j<output[0].length; ++j) {
				System.out.print(output[i][j] +" ");
			}
			System.out.println();
		}
	}

	@Test
	public void test1() {
		RotateImage ri = new RotateImage();
		int[][] matrix1 = { {1,2,3,4},
							{5,6,7,8},
							{9,10,11,12}};
		output = ri.rotateMN(matrix1);
		
	}
	
	@Test
	public void test2() {
		RotateImage ri = new RotateImage();
		int[][] matrix1 = { {1,2,3},
							{5,6,7},
							{9,10,11}};
		output = ri.rotateMN(matrix1);
		
	}

}
