package slicing.com.object;

public class Test{
	public static void main(String[]args){
		Book book = new Book("《java編程》","明日科技",89);
		System.out.println("書名：" + book.getBook_name());
		System.out.println("作者：" + book.getBook_autor());
		System.out.println("價格：" + book.getPrice() + "元");

	}
}
