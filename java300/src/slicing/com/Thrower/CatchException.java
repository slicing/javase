package slicing.com.Thrower;

public class CatchException {
	public static void main(String[]args){
		try{
			@SuppressWarnings("unused")
					Class<?>clazz = Class.forName("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {

		}
	}
}
