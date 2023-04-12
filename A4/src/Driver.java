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
* This class represents the driver program for managing a list of Book objects.
*
*/
public class Driver {
	/**
	 * The main method of the program.
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int input=0;
		ArrayList<Book> arrLst=new ArrayList<Book>();
		BookList bkLst=new BookList();
		bkLst.IncorrectRecords(arrLst);
		System.out.println("Here are the contents of the list");
		System.out.println("=================================");
		bkLst.displayContent();
		System.out.println();
		
		do {
			System.out.println(
					  "Tell me what you want to do? Let's Chat since this is trending now! Here are the options:\r\n"
					+"1) Give me a year # and I would extract all records of that year and store them in a file for that year;\r\n"
					+ "2) Ask me to delete all consecutive repeated records;\r\n"
					+ "3) Give me an author name and I will create a new list with the records of this author and display them;\r\n"
					+ "4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\r\n"
					+ "5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\r\n"
					+ "6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\r\n"
					+ "7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\r\n"
					+ "8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!");
			System.out.print("Enter your Selection: ");
			input=sc.nextInt();
			if(input==1) {
				System.out.println();
				System.out.print("What year would you like to extract: ");
				bkLst.storeRecordsByYear(sc.nextInt());
				System.out.println();
			}
			else if (input==2) {
				bkLst.delConsecutiveRepeatedRecords();
				System.out.println();
				System.out.println("Here are the contents of the list after removing consecutive duplicates");
				System.out.println("========================================================================");
				bkLst.displayContent();
				System.out.println();
			}
			else if(input==3) {
				System.out.println();
				System.out.print("Please enter the name of the author to create an extracted list: ");
				sc.nextLine();
				String aut=sc.nextLine();
				BookList b=bkLst.extractAuthList(aut);
				if(b!=null) {
				System.out.println("Here are the contents of the author list for "+ aut );
				System.out.println("==================================================");
				b.displayContent();
				}
				else {
					System.out.println("Cannot display list");
				}
				System.out.println();
			}
			else if(input==4) {
			    System.out.println();
			    System.out.print("Please enter an ISBN number that you are looking for: ");
			    Long isbn=sc.nextLong();
			    System.out.println();
			    sc.nextLine(); 
			    System.out.print("Please enter the title of the book you want to add: ");
			    String title=sc.nextLine();
			    System.out.print("Please enter the author of the book you want to add: ");
			    String author=sc.nextLine();
			    System.out.print("Please enter the price of the book you want to add: ");
			    double price=sc.nextDouble();
			    sc.nextLine(); 
			    System.out.print("Please enter the isbn of the book you want to add: ");
			    Long isbn1=sc.nextLong();
			    sc.nextLine(); 
			    System.out.print("Please enter the genre of the book you want to add: ");
			    String genre=sc.nextLine();
			    System.out.print("Please enter the year of the book you want to add: ");
			    int year=sc.nextInt();
			    Book b=new Book(title,author,price,isbn1,genre,year);
			    boolean check=bkLst.insertBefore(isbn, b);
			    if(check==false) {
			    	System.out.println("Could not locate ISBN that you entered");
			    	System.out.println();
			    }
			    else {
			    System.out.println();
			    System.out.println("Here are the contents of the list after inserting new node");
			    System.out.println("===========================================================");
			    bkLst.displayContent();
			    System.out.println();
			    }
			}
			else if(input==5) {
				System.out.println();
			    System.out.print("Please enter both ISBN numbers that you are looking for: ");
			    Long isbn1=sc.nextLong();
			    Long isbn2=sc.nextLong();
			    System.out.println();
			    sc.nextLine(); 
			    System.out.print("Please enter the title of the book you want to add: ");
			    String title=sc.nextLine();
			    System.out.print("Please enter the author of the book you want to add: ");
			    String author=sc.nextLine();
			    System.out.print("Please enter the price of the book you want to add: ");
			    double price=sc.nextDouble();
			    sc.nextLine(); 
			    System.out.print("Please enter the isbn of the book you want to add: ");
			    Long isbn3=sc.nextLong();
			    sc.nextLine(); 
			    System.out.print("Please enter the genre of the book you want to add: ");
			    String genre=sc.nextLine();
			    System.out.print("Please enter the year of the book you want to add: ");
			    int year=sc.nextInt();
			    Book b=new Book(title,author,price,isbn3,genre,year);
			    boolean check=bkLst.insertBetween(isbn1,isbn2, b);
			    if(check==false) {
			    	System.out.println("Could not locate the two consecutive ISBN numbers that you entered");
			    	System.out.println();
			    }
			    else {
			    System.out.println();
			    System.out.println("Here are the contents of the list after inserting new node");
			    System.out.println("========================================================================");
			    bkLst.displayContent();
			    System.out.println();
			    }
			}
			else if (input==6) {
				System.out.println();
				System.out.print("Enter two ISBN to swap their location: ");
				long isbn1=sc.nextLong();
				long isbn2=sc.nextLong();
				boolean check=bkLst.swap(isbn1, isbn2);
				if (check==true){
					System.out.println("Records with ISBN, "+isbn1+" and "+isbn2+" were found and te swapped.Here are the contents of the list after rearranging the record");
					System.out.println("=====================================================================");
					bkLst.displayContent();
					System.out.println();
				}
				else
					System.out.println("both ISBN numbers not found");
				
			}
			else if(input==7) {
				System.out.println();
				bkLst.commit();
;				System.out.println("File commited.");
			}

		}while(input!=8);
		System.out.println();
		System.out.println("Exiting Program!");

	}

}
