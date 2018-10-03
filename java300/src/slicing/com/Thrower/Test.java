package slicing.com.Thrower;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test {
	public static void main(String[]args){
		int[]arr = new int[5];
		Arrays.fill(arr,5);
		for (int i=4;i>-1;i--){
			if(i==0){
				throw new DivideZeriException("除零异常");
			}
		}
	}
}
