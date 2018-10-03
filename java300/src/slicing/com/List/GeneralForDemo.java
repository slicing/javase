package slicing.com.List;

import java.util.ArrayList;
import java.util.List;

public class GeneralForDemo {
	public static void main(String[]args){
		List<Integer>list = new ArrayList<Integer>();
		for(int i =1;i < 10;i++){
			list.add(i);
		}
		for (int i=1;i<list.size();i+= 2){
			System.out.print(list.get(i) + "\t");
		}
	}


}
