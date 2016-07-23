package definition;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Word2WordTest {

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
	public void test6() {
//		HashSet<String[]> met1 = new HashSet<String[]>();
//		met1.add(new String[]{"a", "b"});
//		System.out.println(met1.contains(new String[]{"a","b"}));
//		
//		HashMap<String[], Boolean> met2 = new HashMap<String[], Boolean>();
//		met2.put(new String[]{"a", "b"}, true);
//		System.out.println(met2.get(new String[]{"a", "b"}));
		
		HashSet<Word2Word> met3 = new HashSet<Word2Word>(); 
		Word2Word cet = new Word2Word(null, "cet", 1);
		Word2Word bet = new Word2Word(null, "bet", 1);
		
//		System.out.println(met3.contains(new Word2Word(cet, "cet", 2)));
//		System.out.println(cet.equals(new Word2Word(cet, "cet", 2)));
		
		Word2Word c2b = new Word2Word(cet, "bet", 1);
		Word2Word b2c = new Word2Word(bet, "cet", 1);
		Word2Word b2c2 = new Word2Word(bet, "cet", 2);
		System.out.println(c2b.equals(b2c));
		System.out.println(b2c.equals(c2b));
		met3.add(b2c);
		System.out.println(met3.contains(c2b));
	}

}
