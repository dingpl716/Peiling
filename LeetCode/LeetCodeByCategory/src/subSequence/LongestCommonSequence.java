package subSequence;

/**
 * 最长公共子序列：
 * 现有两个字符串 X和 Y
 * 设C[i,j]代表X[0..i]子串和Y[0..j]子串的最长公共子序列长度，则有
 * 
 * c[i,j] = 0, 							if i = 0 or j = 0;
 * 			c[i-1, j-1] + 1 			if x[i] = y[j];
 * 			max(c[i, j-1], c[i-1, j])	if x[i] != y[j];
 * 
 * 
 * @author Dingp
 *
 */
public class LongestCommonSequence {

}
