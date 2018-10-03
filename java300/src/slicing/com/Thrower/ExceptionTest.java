package slicing.com.Thrower;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.rmi.transport.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class ExceptionTest {
	public static void main(String[]args){
		/*System.out.println("-1.0/0 = " + (-1.0/0));
		System.out.println("+1.0/0 = " + (+1.0/0));
		try{
			System.out.println("-1/0 = "+(-1/0));
		}catch (Exception e){
			System.out.println("抛出异常" + e.getMessage());
		}
		System.out.println("+1/0 = " + (+1/0));

		int[]arr = new int[5];
		Arrays.fill(arr,8);
		for(int i =0;i<6;i++){
			System.out.println("arr[" + i + "]=" + arr[i]);
		}
		String string = null;
		System.out.println(string.toLowerCase());

		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}


		Class<?>clazz = String .class;
		Field[]fields = clazz.getDeclaredFields();
		for(Field field:fields){
			if(field.getName().equals("hash")){
				try {
					System.out.println(field.getInt("hash"));
				}catch (IllegalAccessException e){
					e.printStackTrace();
				}
			}
		}


		FileInputStream fis = null;
		try {
			File file = new File("d\\kira.txt");
			fis = new FileInputStream(file);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/



		String URL = "jdbc:mysql://localhost:3306/db_database";
		String DRIVER = "com.mysql.jdbc.Driver";
		String USERNAME = "mr";
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = (Connection) DriverManager.getConnection(URL,USERNAME,"");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
