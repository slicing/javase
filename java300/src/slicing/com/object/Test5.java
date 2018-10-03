package slicing.com.object;

import sun.awt.image.ByteInterleavedRaster;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test5 {
	public static void main(String[]args){
		List<Employee2> employee2s = new ArrayList<Employee2>();
		Employee2 employee2 = new Employee2("李小溪",25);
		long currentTime = System.currentTimeMillis();
		for(int i =0;i<10000;i++){
			employee2s.add(employee2.clone());
		}
		System.out.println("克隆花费时间" + (System.currentTimeMillis() - currentTime) + "毫秒");
		currentTime = System.currentTimeMillis();
		for(int i =0;i<10000;i++){
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = null;
			try{
				out = new ObjectOutputStream(baos);
				out.writeObject(employee2);
			} catch (IOException e) {
				e.printStackTrace();
			}finally {

			}
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream in = null;
			try{
				in =new ObjectInputStream(bais);
				employee2s.add((Employee2)in.readObject());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {

			}
		}
		System.out.println("序列话费时间：" + (System.currentTimeMillis() - currentTime) + "毫秒");
	}
}
