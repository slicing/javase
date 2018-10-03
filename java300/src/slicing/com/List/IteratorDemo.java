package slicing.com.List;

import java.util.*;

public class IteratorDemo {
	public static void main(String[]args){
		TreeSet<Integer> set = new TreeSet<Integer>();
		Random ran = new Random();
		int count = 0;
		while (count<10){
			boolean succed = set.add(ran.nextInt(100));
			if(succed){
				count++;
			}
		}
		int size = set.size();
		Integer[]arr = new Integer[size];
		set.toArray(arr);
		for(int value:arr){
			System.out.print(value+"\t");
		}
		/*List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		*//*for(Iterator<Integer> it = list.iterator();it.hasNext();){
			System.out.print(it.next()+"\t");
		}*//*
		ListIterator<Integer> li = list.listIterator();
		for(li = list.listIterator();li.hasNext();){
			li.next();
		}
		for(;li.hasPrevious();){
			System.out.print(li.previous()+"\t");
		}*/
	}
}
