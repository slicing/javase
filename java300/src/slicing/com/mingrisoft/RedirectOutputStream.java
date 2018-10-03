package slicing.com.mingrisoft;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RedirectOutputStream {
	public static void main(String[]args){
		try{
			PrintStream out = System.out;
			PrintStream ps = new PrintStream("./log.txt");
			System.setOut(ps);
			int age = 18;
			String sex = "女";
			String info = "这是个" + sex + "孩纸，应该有" + age + "岁了。";
			System.setOut(out);
			System.out.println("查看日志");
			System.out.println();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
