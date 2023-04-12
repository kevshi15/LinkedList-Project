import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


//-----------------------------------------------------
//Assignment 4
//Written by: Kevin Shibu Chacko 40241154 & Andrew Harissi Dagher 40247726 
//
//-----------------------------------------------------

/**
* 
* @author Kevin Shibu Chacko
* @author Andrew Harissi Dagher
* Class defines a BookList class with various methods to manipulate a linked list of Book objects. 
*
*/
/**
 * A linked list of Book objects.
 */
public class BookList {
	
	 /**
     * A node in the linked list.
     */
	private class Node{
		private Book b;
		private Node next;
	/**
    * Constructs a new Node with null values.
    */
		public Node() {
			b=null;
			next=null;
		}
		 /**
         * Constructs a new Node with the specified Book and next Node.
         *
         * @param b The Book to store in the Node.
         * @param link The next Node in the list.
         */
		public Node(Book b,Node link) {
			this.b=b;
			next=link;
		}
	}
	
	private Node head;
	
	/**
     * Constructs a new empty BookList.
     */
	public BookList() {
		head=null;
	}
	 /**
     * Reads Book objects from a file and separates out the ones with a publication year after 2023.
     * The invalid Books are stored in an ArrayList for later processing.
     *
     * @param incRec An ArrayList to store invalid Books in.
     */
	public void IncorrectRecords(ArrayList<Book> incRec) {
		Scanner sc=null;
		PrintWriter pw=null;
		String line="";
		String arr[]=null;
		Book b=null;
		try {
			sc=new Scanner(new FileInputStream("Books.txt"));
			while(sc.hasNextLine()) {
				line=sc.nextLine();
				arr=line.split(",");
				b = new Book(arr[0],arr[1],Double.parseDouble(arr[2]),Long.parseLong(arr[3]),arr[4],Integer.parseInt(arr[5]));
				
				if(b.getYear()>2023) {
					incRec.add(b);
				}
				else {
					addAtEnd(b);
				}
		
			}
			sc.close();
			pw=new PrintWriter(new FileOutputStream("YearErr.txt"));
			for(Book b1: incRec) {
				pw.println(b1);
			}
			pw.close();		
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found");
		}
		System.out.println("YearError File Created");
	}
	 /**
     * Adds a Book to the start of the list.
     *
     * @param b The Book to add to the list.
     */
	public void addToStart(Book b) {
	    Node n = new Node(b, head);
	    if (head == null) {
	        head = n;
	        head.next = head; 
	    } else {
	        Node temp = head;
	        while (temp.next != head) {
	            temp = temp.next;
	        }
	        temp.next = n; 
	        head = n; 
	    }
	}
	 /**
     * Adds a Book to the end of the list.
     *
     * @param b The Book to add to the list.
     */
	public void addAtEnd(Book b) {
	    if (head == null) {
	        Node n = new Node(b, null);
	        head = n;
	        head.next = head;
	    } else {
	        Node temp = head;
	        while (temp.next != head) {
	            temp = temp.next;
	        }
	        temp.next = new Node(b, head);
	    }
	}
	 /**
     * Stores all Books from a given year in a file named with the year.
     *
     * @param yr The year to extract Books from.
     */
	public void storeRecordsByYear(int yr) {
	    String str = Integer.toString(yr) + ".txt";
	    PrintWriter pw = null;
       boolean created = false;
	    try {
	        Node temp = head;
	        while (temp.next != head) {
	            if (temp.b.getYear() == yr) {
	            	pw = new PrintWriter(new FileOutputStream(str), true);
	                pw.println(temp.b);
	                created=true;
	            }
	            temp = temp.next;
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found");
	    }
	    if(created) {
	    pw.close();
	    System.out.println("File "+str+" created");
	    System.out.println();
	    }
	    else {
	    	System.out.println("File was not created.Invalid year");
	    	System.out.println();
	    }
    
	}

	/**
	Inserts a new book before the book with the given ISBN.
	@param isbn the ISBN of the book before which the new book will be inserted
	@param b the new book to be inserted
	@return true if the new book is successfully inserted; false otherwise
	*/
	public boolean insertBefore(long isbn, Book b) {
		if (head == null) {
	        return false;
	    }
		if(head.b.getIsbn()==isbn) {
			head = new Node(b, head);  
			return true;
		}
		Node temp = head;
		
		while(temp.next.b.getIsbn() != isbn) {
				temp = temp.next;
			if (temp.next == head)
			{
				return false;
			}
		}
			temp.next = new Node(b, temp.next);	
			return true;
	}
	/**
	Inserts a new book between two books with the given ISBNs.
	@param isbn1 the ISBN of the book before which the new book will be inserted
	@param isbn2 the ISBN of the book after which the new book will be inserted
	@param b the new book to be inserted
	@return true if the new book is successfully inserted; false otherwise
	*/
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		if (head == null) {
	        return false;
	    }
	    if (head.b.getIsbn() == isbn1 && head.next.b.getIsbn() == isbn2) {
	        head.next= new Node(b, head.next);
	        return true;
	    } else {
	        Node temp = head.next;
	        while (temp.b.getIsbn() != isbn1 || temp.next.b.getIsbn() != isbn2) {
	            temp = temp.next;
	            if (temp.next == head) {
	                return false;
	            }
	        }
	        temp.next = new Node(b, temp.next);
	        return true;
	    }
	}
	/**
	Deletes consecutive repeated records from the list.
	@return true if any consecutive repeated records are found and deleted; false otherwise
	*/
	public boolean delConsecutiveRepeatedRecords() {
		if (head == null) {
	        return false;
	    }
	    Node temp1 = head;
	    Node temp2 = head.next;
	    boolean ans = false;
	     while((temp2.next != head)) {
	        if (temp1.b.equals(temp2.b)) {
	            Node temp3 = temp2;
	            temp1.next = temp2.next;
	            temp3.next = null;
	            temp2 = temp1.next;
	            ans = true;
	        } else {
	            temp1 = temp1.next;
	            temp2 = temp2.next;
	        }
	    } 
	   
	    if (temp1.b.equals(temp2.b)) {
	        temp1.next = head;
	        temp2.next = null;
	        ans = true;
	    }
	   
	    if(temp1.b.equals(head.b)) {
	    	Node temp3=head;
	    	temp1.next=head.next;
	    	head=head.next;
	    	temp3.next=null;
	    	ans=true;
	    }
	    return ans;
	}
	/**
	Extracts a new book list that contains all the books with the given author.
	@param aut the author of the books to be extracted
	@return a new BookList object containing all the books with the given author; null if no books are found
	*/
	public BookList extractAuthList(String aut) {
		if (head == null) {
	        return null;
	    }
		BookList b1=new BookList();
		if(head.b.getAuthor().equals(aut)) {
			b1.addAtEnd(head.b);
		}
		Node temp=head.next;
		while(temp!=head) {
			if(temp.b.getAuthor().equals(aut)){
				b1.addAtEnd(temp.b);
			}
			temp=temp.next;
		}
		if(b1.head==null) {
			return null;
		}
		return b1;
	}
	/**
	Swaps the positions of two books with the given ISBNs.
	@param isbn1 the ISBN of the first book to be swapped
	@param isbn2 the ISBN of the second book to be swapped
	@return true if the books are successfully swapped; false otherwise
	*/
	public boolean swap(long isbn1, long isbn2) {
		if (head == null) {
	        return false;
	    }
		Node temp1=head;
		Node temp2=head.next;
		while(temp1.b.getIsbn()!=isbn1) {
			temp1=temp1.next;
			if(temp1==head) {
				return false;
			}
		}
		while(temp2.b.getIsbn()!=isbn2) {
			temp2=temp2.next;
			if(temp2==head.next) {
				return false;
			}
		}
		Book temp3=temp1.b;
		temp1.b=temp2.b;
		temp2.b=temp3;
		return true;
	}
	/**
	Writes the current state of the book list to a file named "Update_Books.txt".
	*/
	public void commit() {
		Node temp=head;
		PrintWriter pw = null;
		try {
			 pw = new PrintWriter(new FileOutputStream("Update_Books.txt"));
			 while(temp.next!=head) {
				 pw.println(temp.b);
				 temp=temp.next;
			 }
		}catch (FileNotFoundException e) {
	        System.out.println("File not Found");
		}
		pw.close();
	}
	/**
	Displays the content of the book list on the console.
	*/
	public void displayContent() {
	    Node temp = head;
	    do {
	        System.out.println(temp.b + " ==>");
	        temp = temp.next;
	    } while(temp != head);
	    System.out.println("===> head");
	}
	

}
