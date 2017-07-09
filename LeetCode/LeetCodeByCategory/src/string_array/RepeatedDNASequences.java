package string_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

//	All DNA is composed of a series of nucleotides 
//	abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
//	When studying DNA, it is sometimes useful to identify 
//	repeated sequences within the DNA.
//	
//	Write a function to find all the 10-letter-long sequences (substrings) 
//	that occur more than once in a DNA molecule.
//	
//	For example,
//	
//	Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
//	
//	Return:
//	["AAAAACCCCC", "CCCCCAAAAA"].

public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> results = new ArrayList<String>();
        
        if (s != null && s.length() > 10) {
        	results = findSequeces(s);
        }
        
        return results;
    }
    
    public List<String> findSequeces(String s) {
    	Set<String> results = new HashSet<String>();
    	Set<String> hasMet = new HashSet<String>();
    	
    	for (int i = 0; i <= s.length() - 10; ++i) {
    		String sequence = s.substring(i, i+10);
    		if (hasMet.contains(sequence)) {
    			results.add(sequence);
    		}else {
    			hasMet.add(sequence);
    		}
    	}
    	
    	String[] a = new String[results.size()]; 
    	results.toArray(a);
    	
    	return Arrays.asList(a);
    }
    
    public static void main(String[] args){
    	RepeatedDNASequences r = new RepeatedDNASequences();
    	List<String> results = r.findRepeatedDnaSequences("AAAAAAAAAAA");
    	Util.PrintListOfString(results);
    }
}
