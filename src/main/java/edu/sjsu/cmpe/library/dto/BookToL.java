package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.domain.Book;


/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 *
 */



@JsonPropertyOrder(alphabetic = true)
public class BookToL {

	
	private long isbn;
    private String title;
    private String date;		// required	
    private String language;

    private int numPage;
    private String status;
    private List<LinkDto> reviews = new ArrayList<LinkDto>();
	private List<LinkDto> authors = new ArrayList<LinkDto>();;
	
	
	public BookToL(Book book)
	{
		super();
		setIsbn(book);
		setTitle(book);
		setDate(book);
		setLanguage(book);
		setNumPage(book);
		setStatus(book);
		setReviews(book);
		setAuthors(book);
		
	}
	
	
	public void setIsbn(Book book)
	{
		isbn = book.getIsbn();
	}
	
	public long getIsbn()
	{
		return isbn;
	}
	
	public void setTitle(Book book)
	{
		title = book.getTitle();
	}
	
	
	public String getTitle()
	{
		return title;
	}
	
	@JsonProperty("publication-date")
	public void setDate(Book book)
	{
		date = book.getDate();
	}

	
	public String getDate()
	{
		return date;
	}
	
	public void setLanguage(Book book)
	{
		language = book.getLanguage();
	}
	
	
	public String getLanguage()
	{
		return language;
	}
	
	@JsonProperty("num-pages")
	public void setNumPage(Book book)
	{
		numPage = book.getNumPage();
	}
	
	
	public long getNumPage()
	{
		
		return numPage;
	}
	
	public void setStatus(Book book)
	{
		status = book.getStatus();
	}
	
	
	public String getStatus ()
	{
		return status;
	}
	
	
	public void setReviews(Book book)
	{
		
		for(Review review: book.getReviews())
		{
			String str = "/books/" + isbn + "/reviews/" + review.getId();
			reviews.add(new LinkDto("view-review", str, "GET"));
		}	
	}
	
	
	public List<LinkDto> getReviews()
	{
		return reviews;
	}
	
	
	
	
	public void setAuthors (Book book)
	{
		for(Author author: (ArrayList<Author>) book.getAuthors())
		{
			System.out.println("Author: \n" + author.printTrace() );
			String str = "/books/" + isbn + "/authors/" + author.getId();
			System.out.println(str);
			authors.add(new LinkDto("view-author", str, "GET"));
		}		
		
	}
	
	public List<LinkDto> getAuthors()
	{
		return authors;
	}
	
	
	
}
