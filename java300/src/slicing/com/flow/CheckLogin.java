package slicing.com.flow;

import java.util.Scanner;

public class CheckLogin {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String usename = scn.nextLine();
		System.out.println("请输入密码：");
		String passwd = scn.nextLine();
		if (!usename.equals("slicing")){
			System.out.println("用户名非法");
		}else if(!passwd.equals("slicing")){
			System.out.println("密码错误");
		}else{
			System.out.println("恭喜你登录成功");
		}

	}
}
