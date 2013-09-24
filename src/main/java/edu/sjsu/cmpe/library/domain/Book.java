package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;



/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * Details of the book
 */



public class Book {
	private final static String DEFAULT_STATUS = "available";
	private static long key = 0;
	private long isbn;
	
	@NotEmpty(message ="required")
	private String title;
	
	@NotEmpty(message ="required")
	private String date;		// required	
    private String language;

    private int numPage;
    private String status = DEFAULT_STATUS;
    private List<Review> reviews = new ArrayList<Review>();
	private List<Author> authors = new ArrayList<Author>();
	


	public Book()
	{
		isbn = ++key;
	}
	

	/** setter and getter methods
	 * 
	 * @param key
	 */
	
	public void setIsbn(Long key)
	{
		isbn = key;
	}
	
	
	public long getIsbn()
	{
		return isbn;
	}
	
	
	
	/*** setter and getter methods
	 * 
	 * @param title
	 */
	
	public void setTitle(String title)
	{
		this.title = checkNotNull(title, "title must not be null");

	}

	public String getTitle()
	{

		return title;
	}    

	
	/**** setter and getter methods
	 * 
	 * @param lang
	 * @return
	 */
	
	public boolean setLanguage (String lang)
	{
		if(lang == null)
			return false;

		language = lang;	
		return true;
	}

	public String getLanguage()
	{

		return language;
	}

	
	
	/**** setter and getter methods
	 * 
	 * @param au
	 */
	
	@JsonProperty("authors")
	public void setAuthors(List<Author> au)
	{
		authors= au;
	
	}
	
	
	
	@JsonProperty("authors")
	public List<Author> getAuthors()
	{
		return authors;
	}
	
	
	
	
	public boolean isAuthorEmpty()
	{
		return authors.isEmpty();
	}
	
	
	
	/**** return author if found
	 * 
	 * @param id
	 * @return
	 */
	
	public Author searchAuthorByID(long id)
	{
		
		for(Author author: getAuthors())
			if(author.getId() == id )
				return author;
		
		return null;
		
	}
	
	
	
	/**** setter and getter methods
	 * 
	 * @param reviews
	 */
	
	@JsonProperty("reviews")
	public void setReviews(List<Review> reviews)
	{
		
		this.reviews = reviews;
	}
	
	
	
	
	public void addReview(Review review)
	{
		reviews.add(review);
	}


	
	
	public List<Review> getReviews()
	{
		return reviews;
	}
	
	
	
	/*** return review if found
	 * 
	 * @param id
	 * @return
	 */
	public Review searchReviewByID(Long id)
	{
		for(Review review: getReviews())
			if(review.getId() == id )
				return review;
		
		return null;
		
	}
	
	
	
	public boolean isReviewEmpty()
	{
		return reviews.isEmpty();
	}
	
	
	
	/**** setter and getter methods
	 * 
	 * @param date
	 */
	@JsonProperty("publication-date")
	public void setDate(String date)
	{
		
		this.date = checkNotNull(date, "Date must not be null");
	}  


	
	public String getDate()
	{
		return date;

	}


	// {available, checked-out, in-queue, or lost}
	public void setStatus (String status)
	{
		if(status.equals("checked-out") || 
				status.equals("in-queue") ||
				status.equals("lost") )
			this.status= status;

	
	}


	
	
	public String getStatus()
	{
		return status;

	}


	
	
	@JsonProperty("num-pages")
	public void setNumPage(int num)
	{
		numPage = num;

	}

	
	

	public int getNumPage()
	{

		return numPage;
	}

	
	
	
	public String printTrace()
	{
	  String str = "isbn: " + isbn + "\n" +
		"title:" + title + "\n" +
		"publication-date: " + date + "\n" +
		"language: " + language + "\n" +
		"num-pages: " + numPage + "\n" +
		"status: " + status + "\n" +
		"reviews: ";

	  for (Review review: reviews)
		str += review.printTrace() + "\n";

	  str += "Authors: ";

	  for (Author author: authors)
		str += author.printTrace() + "\n";

	  return str;
	  
	}

}

