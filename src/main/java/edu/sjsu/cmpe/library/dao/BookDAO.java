package edu.sjsu.cmpe.library.dao;

import java.util.HashMap;

import edu.sjsu.cmpe.library.domain.Book;


/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * Book DAO keep the list of books in the library 
 */

public class BookDAO {

	private static long count = 0;
	private static HashMap<Long, Book> books;
	
	// constructor
	public BookDAO ()
	{
		books = new HashMap<Long, Book>();
		
	}	
	
	
	// return # of books
	public static long getNumBooks()
	{
		return count;
	}
	
	
	// check if the repository is empty
	public static boolean isEmpty()
	{
		return books.isEmpty();
	}
	
	
	// check if book is in repository
	public boolean isFound(long key)
	{
		return books.containsKey(key);
	}
	
	
	// return book if found using the key
	public Book searchByID(long key)
	{
		Book bk;
		
		
		System.out.println("SEARCH BY ID= " + key);
		
		bk = (Book) books.get(key);
		
		System.out.println(bk.printTrace());
		
		
		return bk;
		
	}
	
	
	// add the book to the repository
	public Book addBookList(long key, Book book)
	{
		books.put(key, book);
		count++;
		return book;
		
	}
	
	
	
	// update the book
	public boolean updateBookList(long key, Book book)
	{
		if(!books.containsKey(key))
			return false;
			
		books.put(key, book);
		return true;
	}
	
	
	
	// delete book from repository
	public Book removeFromBookList(long key)
	{
		count--;
		return books.remove(key);
		
		
	}
}
