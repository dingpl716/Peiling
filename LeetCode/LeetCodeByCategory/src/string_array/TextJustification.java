package string_array;
import java.util.ArrayList;

//	Given an array of words and a length L, format the text such that each 
//	line has exactly L characters and is fully (left and right) justified.
//	
//	You should pack your words in a greedy approach; that is, pack as many 
//	words as you can in each line. Pad extra spaces ' ' when necessary so that 
//	each line has exactly L characters.
//	
//	Extra spaces between words should be distributed as evenly as possible. 
//	If the number of spaces on a line do not divide evenly between words, 
//	the empty slots on the left will be assigned more spaces than the slots on the right.
//	
//	For the last line of text, it should be left justified 
//	and no extra space is inserted between words.

//For example,
//words: ["This", "is", "an", "example", "of", "text", "justification."]
//L: 16.
//
//Return the formatted lines as:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//Note: Each word is guaranteed not to exceed L in length.
public class TextJustification {

	public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> text = new ArrayList<String>();
        
        if(words == null || words.length == 0)
            return text;
        
        int start = 0;
        int end = getWords(words, start, L);
        while(end != words.length-1) {  // for the lines other than last line
            int[] spaces = calculateSpaces(words, start, end, L);
            String line = generateLine(words, start, end, spaces);
            text.add(line);
            start = end + 1;
            end = getWords(words, start, L);
        }
        
        // generate the last line;
        String lastLine = generateLastLine(words, start, end, L);
        text.add(lastLine);
        return text;
    }
    
    /*
     * this function gets the words that we are going to put on a line.
     * return: end, such that the words belongs to words[start, end]
     */
    private int getWords(String[] words, int start, int l) {
        int currentL = words[start].length();
        int end = start;
        
        while(end+1<words.length) {
            if(currentL + 1 + words[end+1].length() > l)
                break;
            else {
                currentL = currentL + 1 + words[end+1].length(); //plus an additional space
                end++;
            }
        }
        
        return end;
    }

    // calculate how we insert spaces  
    private int[] calculateSpaces(String[] words, int start, int end, int l) {
    	int slotsCount = end - start;  // in each slot we insert certain number of spaces
    	if(slotsCount == 0) {
			slotsCount = 1;
    	}
        int wordsLength = 0; // the total length of words without counting spaces
        for(int i=start; i<=end; ++i) {
            wordsLength += words[i].length();
        }
        
        int spacesCount = l - wordsLength; // how many spaces we need to distribute
        int spaces[] = new int[slotsCount];    //spaces[i] means that after i-th word, we need to insert spaces[i] spaces
        int i = 0;
        while(spacesCount > 0) {
            spaces[i%slotsCount]++;
            spacesCount--;
            i++;
        }
        return spaces;
    }
    
    
    private String generateLine(String[] words, int start, int end, int spaces[]) {
        StringBuffer line = new StringBuffer();
        line.append(words[start]);
        
        for(int i=0; i<spaces.length; ++i) {
            StringBuffer spacesString = generateSpaces(spaces[i]);
            line.append(spacesString);
            if(start + i + 1<=end)
            	line.append(words[start + i + 1]);
        }
        
        return line.toString();
    }
    
    private StringBuffer generateSpaces(int n) {
        StringBuffer spaces = new StringBuffer();
        for(int i=0; i<n; ++i)
            spaces.append(' ');
        
        return spaces;
    }
    
    private String generateLastLine(String[] words, int start, int end, int l) {
        StringBuffer lastLine = new StringBuffer();
        lastLine.append(words[start]);
        
        for(int i=start+1; i<=end; ++i) {
            lastLine.append(' ');
            lastLine.append(words[i]);
        }
        
        int lastSpaces = l - lastLine.length();
        lastLine.append(generateSpaces(lastSpaces));
        
        return lastLine.toString();
    }
    
	public static void main(String[] args) {
		
		TextJustification t = new TextJustification();
		String[] words = {"Listen","to","many,","speak","to","a","few."};
		t.fullJustify(words, 6);
	}

}
