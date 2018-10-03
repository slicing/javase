package slicing.com.object;

public class Book {
	private String book_name;
	private String book_autor;
	private int price;
	public Book(String book_name,String book_autor,int price){
		this.book_name = book_name;
		this.book_autor = book_autor;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public String getBook_autor() {
		return book_autor;
	}

	public String getBook_name() {
		return book_name;
	}
}
