
//-----------------------------------------------------
//Assignment 3
//Question: Part 2
//Written by: Kevin Shibu Chacko 40241154 & Andrew Harissi Dagher 40247726 
//
//-----------------------------------------------------

/**
 * 
 * @author Kevin Shibu Chacko
 * @author Andrew Harissi Dagher
 * Class represents book class that stores all valid books into book objects, so they can be serialized.
 *
 */
public class Book {

	private String title;
	private String author;
	private double price;
	private long isbn;
	private String genre;
	private int year;
	
	/**
	 * default constructor 
	 */
	public Book() {
		
	}
	/**
	 * custom constructor with respective elements.
	 * @param title
	 * @param author
	 * @param price
	 * @param isbn
	 * @param genre
	 * @param year
	 */
	public Book(String title, String author, double price, long isbn, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
	
	/**
	 * get title method that return specific title of certain book
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * set title method that will set title to new inputted one in parameter
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * get author method that will return specific author of certain book
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}
    /**
     * set author method that will set author to new inputted one in parameter
     * @param author
     */
	public void setAuthor(String author) {
		this.author = author;
	}
    /**
     * get price method that will return specifc price of certain book
     * @return price
     */
	public double getPrice() {
		return price;
	}
    /**
     * set price method that will set price to new inputted one in parameter
     * @param price
     */
	public void setPrice(double price) {
		this.price = price;
	}
    /**
     * get isbn method that will return specific isbn of certain book
     * @return isbn
     */
	public long getIsbn() {
		return isbn;
	}
    /**
     * set isbn method that wil set isbn to new inputted one in parameter
     * @param isbn
     */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	/**
     * get genre method that will return specific genre of certain book
     * @return genre
     */
	public String getGenre() {
		return genre;
	}
	/**
     * set genre method that will set genre to new inputted one in parameter
     * @param isbn
     */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
     * get year method that will return specific year of certain book
     * @return year
     */
	public int getYear() {
		return year;
	}
	/**
     * set year method that will set year to new inputted one in parameter
     * @param isbn
     */
	public void setYear(int year) {
		this.year = year;
	}
     
	
	@Override
	/**
	 * To string method that will override objects to string method,will outputs all elemetns of that certain book
	 */
	public String toString() {
		return title + ", " + author + ", " + price + ", " + isbn + ", "+ genre + ", " + year;
	}
     
	
	@Override
	/**
	 * equals method that will override its objects equals method, and will return true if both book have exactly same elements
	 */
	public boolean equals(Object obj) {
		
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		else {
		Book book = (Book) obj;
		return (this.title.equals(book.title)&& this.author.equals(book.author)&& this.genre.equals(book.genre)&& this.year==book.year&&this.price==book.price);
		}
	}
	
	
	
	
	
	
	
	
}

