package slicing.com.object;

import java.util.Random;

public class Book1 {
	private static int count =0;
	public Book1(String name){
		System.out.println("售出图书:" + name);
		count++;
	}
	public static int getCount(){
		return count;
	}
	public static void main(String []args){
		String[]name = {"《java编程》","《傲娇啊》","《视频》"};
		for(int  i =0;i<5;i++){
			new Book1(name[new Random().nextInt(3)]);
		}
		System.out.println("共计销售了" + Book1.getCount());
	}
}
