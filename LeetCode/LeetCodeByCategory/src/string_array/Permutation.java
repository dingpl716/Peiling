package string_array;
import java.util.ArrayList;
import java.util.Arrays;


public class Permutation {
//	这个算法是用来以字典序列产生下一个大于目前的数的数
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        if(num == null)
            return permutations;
            
        Arrays.sort(num);
        permutations.add(toArrayList(num));
//        从右向左看，如果整个序列是递增的，那么就说明已经到最大了
        while(true) {
//        	s是指向驼峰
            int s = num.length - 1;
//            b在s的左边
            int b = num.length - 2;
            // 找出递减的地方
            while(s >= 0 && b >=0) {
                if(num[s] <= num[b]){
                    --s;
                    --b;
                }
                else 
                    break;
            }
            
            if(s <= 0)
                break;
//            此时[s，end]的部分已经最大了,所以需要扩展到[b,end]
//            	所以要找到找到刚比b大的数 
            int n = num.length - 1;
            while(num[n] <= num[b]) {
                --n;
            }
            
            swap(num, b , n);
//            现在的b比之前的那个b刚好大一点点，而[s,end]已经最大了，所以reverse这部分
//            让他变成最小的
            reverse(num, s);
            permutations.add(toArrayList(num));
        }
        
        return permutations;
    }
    
    private void swap(int[] num, int s, int b) {
        int tmp = num[s];
        num[s] = num[b];
        num[b] = tmp;
    }
    
    private void reverse(int[] num, int left) {
        int right = num.length - 1;
        while(left < right){
            swap(num, left, right);
            ++left;
            --right;
        }
    }
    
    private ArrayList<Integer> toArrayList (int[] num) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int i=0; i<num.length; ++i)
            list.add(num[i]);
        
        return list;
    }
}
