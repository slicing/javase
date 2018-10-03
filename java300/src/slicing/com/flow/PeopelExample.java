package slicing.com.flow;

import java.util.Scanner;

public class PeopelExample {
	public static void main(String[]args){
		Scanner scn = new Scanner(System.in);
		String name = scn.nextLine();
		String language = scn.nextLine();
		switch (language.hashCode()){
			case 3254818:
			case 2301506:
			case 2269730:
				System.out.println("员工" + name + "被分配到java");
		}
	}
}
