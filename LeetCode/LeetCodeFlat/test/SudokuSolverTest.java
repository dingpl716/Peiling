import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SudokuSolverTest {

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
		SudokuSolver s = new SudokuSolver();
		String[] str = {"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
		char[][] board = new char[9][9];
		for(int i=0; i<str.length; ++i)
			board[i] = str[i].toCharArray();
		s.solveSudoku(board);
		
	}

}
