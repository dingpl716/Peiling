import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class NQueensTest {

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
		NQueens nq = new NQueens();
		ArrayList<String[]> result = nq.solveNQueens(4);
		print(result);
	}
	
	private void print(ArrayList<String[]> result) {
		
		for(int i=0; i<result.size(); ++i) {
			String[] board = result.get(i);
			printBoard(board);
			
			System.out.println("===================");
		}
	}
	
	private void printBoard(String[] board) {
		for(int i=0; i<board.length; ++i) {
			for(int j=0; j<board[0].length(); ++j) {
				System.out.print(board[i].charAt(j) + " ");
			}
			System.out.println();
		}
	}

}
