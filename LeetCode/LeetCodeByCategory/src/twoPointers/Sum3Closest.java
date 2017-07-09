package twoPointers;
import java.util.Arrays;

//	连个指针，left，right，
//	left通过for循环从0到length-3，
//	right在这个循环里面，每次都从length-1开始！
//	再来一个runner，在left和right之间跑，
//	然后不断比较这三者之和，如果他们的和等于target，那么返回就行了，因为找到一模一样的了
//	如果三者之和大于target的话，那么就要在这个和和目前的result里面取最近的一个，然后right-- ！！
//	注意，这里是right--，这是缩小right规模的时候
//	如果三者之后小于target的话，同样取较近的一个，但是这里是runner++，不是left++！！
//	left的规模缩小发生于最外层的for循环，而不在这里

public class Sum3Closest {
    public int threeSumClosest(int[] num, int target) {
        int result = 0;
        
        if(num.length <= 3) {
            for(int i=0; i<num.length; ++i)
                result += num[i];
            return result;
        }
        
        Arrays.sort(num);
        
        result = num[0] + num[1] + num[num.length-1];
        
        for(int left=0; left<=num.length-3; ++left) {
            int right = num.length - 1;
            int runner = left + 1;
            while(runner < right) {
                int tmp = num[left] + num[runner] + num[right];
                if(tmp == target)
                    return tmp;
                if(tmp > target) {  //!!!!!!在这里和target比较的是tmp,在和target比较了之后，才和result比较！
                	result = Math.abs(tmp - target) < Math.abs(result - target) ? tmp : result;
                	right--;
                }
                else {
                	result = Math.abs(tmp - target) < Math.abs(result - target) ? tmp : result;
                	runner++;
                }
            }
        }        
        return result;
    }
}
