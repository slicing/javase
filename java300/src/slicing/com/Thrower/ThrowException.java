package slicing.com.Thrower;

public class ThrowException {
	public static void throwException() throws ClassNotFoundException {
		//throw new UnsupportedOperationException("方法尚未实现");
		Class.forName("com.mysql.jdbc.Driver");
	}
	public static void main(String[]args){
		try {
			ThrowException.throwException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
