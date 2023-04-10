import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
	
	
	private class Node{
		private Book b;
		private Node next;
		
		public Node() {
			b=null;
			next=null;
		}
		public Node(Book b,Node link) {
			this.b=b;
			next=link;
		}
	}
	
	private Node head;
	
	public BookList() {
		head=null;
	}
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

	
	public boolean insertBefore(long isbn, Book b) {
		
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
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
	    if (head.b.getIsbn() == isbn1 && head.next.b.getIsbn() == isbn2) {
	        head = new Node(b, head.next);
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
	public boolean delConsecutiveRepeatedRecords() {
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
      
	public BookList extractAuthList(String aut) {
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
	public boolean swap(long isbn1, long isbn2) {
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
	
	public void displayContent() {
	    Node temp = head;
	    do {
	        System.out.println(temp.b + " ==>");
	        temp = temp.next;
	    } while(temp != head);
	    System.out.println("===> head");
	}
	

}
