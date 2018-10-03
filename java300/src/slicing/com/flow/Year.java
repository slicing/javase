package slicing.com.flow;

import java.util.Scanner;

public class Year {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);

		try{
			long year = scn.nextLong();
			if(year % 4 == 0 && year % 100!=0 || year % 400 ==0){
				System.out.println(year +"是闰年");
			}else {
				System.out.println(year +"不是闰年");
			}
		}catch (Exception e){
			System.out.println("输入的年份无效！");
		}

	}
}
