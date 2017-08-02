package design;

import java.util.ArrayList;

//	Note: This is a companion problem to the System Design problem: Design TinyURL.
//	TinyURL is a URL shortening service where you enter a URL such as 
//	https://leetcode.com/problems/design-tinyurl and 
//	it returns a short URL such as http://tinyurl.com/4e9iAk.
//	
//	Design the encode and decode methods for the TinyURL service. 
//	There is no restriction on how your encode/decode algorithm should work. 
//	You just need to ensure that a URL can be encoded to a tiny URL and the 
//	tiny URL can be decoded to the original URL.

//  Your Codec object will be instantiated and called as such:
//  Codec codec = new Codec();
//  codec.decode(codec.encode(url));

/**
 * 和TinyURL讨论的一致，在这里当我们encode longUrl的时候，我们不做hash，我们只单纯的
 * 在database里面自增一条数据;
 * @author Dingp
 *
 */
public class EncodeAndDecodeTinyURL {

	private static ArrayList<String> database = new ArrayList<String>();
	
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        database.add(longUrl);
        
    	return integerToShortUrl(database.size() - 1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = shortUrlToInteger(shortUrl);
        if (index >= database.size()) {
        	return "";
        } else {
        	return database.get(index);
        }
    }
    
    /*** 以下两个方法实际上是一个十进制和六十二进制的转换 ****/
    private String integerToShortUrl(int index) {
    	StringBuilder url = new StringBuilder();
    	
    	int mod = index;
    	while (mod >= 62) {
    		int quotient = mod / 62;
    		url.append(getChar(quotient));
    		mod = mod % 62;
    	}
    	
    	url.append(getChar(mod));
    	
    	insertDigits(url);
    	
    	return url.toString();
    }
    
    private int shortUrlToInteger(String url) {
    	int result = 0;
    	for (char c : url.toCharArray()) {
    		int tmp = 0;
    		if (c >= '0' && c <= '9') {
    			tmp = c - '0';
    		} else if (c >= 'a' && c <= 'z') {
    			tmp = c - 'a' + 10;
    		} else {
    			tmp = c - 'A' + 36;
    		}
    		
    		result = result * 62 + tmp;
    	}
    	
    	return result;
    }

    private char getChar(int digit) {
    	if (digit <= 9) {
    		return (char) ('0' + (digit));
    	} else if (digit <= 35) {
    		return (char) ('a' + (digit - 10));
    	} else {
    		return (char) ('A' + (digit - 36));
    	}
    }

    private StringBuilder insertDigits(StringBuilder url) {
    	if (url.length() >= 6) {
    		return url;
    	}
    	
    	StringBuilder zeros = new StringBuilder();
    	for (int i = 0; i < 6 - url.length(); ++i) {
    		zeros.append('0');
    	}
    	
    	zeros.append(url);
    	return zeros;
    }
}
