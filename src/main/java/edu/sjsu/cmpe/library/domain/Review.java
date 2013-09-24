package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.HashMap;

import edu.sjsu.cmpe.library.dto.*;

/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * 
 */



public class Review {
	
	private static int count = 0;
	private long id;
	private int rating;	// required
	private String comment;	// required


	// constructor
	public Review()
	{

		id = ++count;

	} 


	/*** setter and getter methods
	 * 
	 * @param rat
	 * @return
	 */
	public boolean setRating(int rat) 
	{
		if( rat > 5 || rat < 1)
			return false; 
		rating = rat;
		return true; 	      

	}   		
	
	
	
	public int getRating()
	{
		return rating;

	}

	
	/**** setter and getter methods
	 * 
	 * @param comm
	 * @return
	 */
	public boolean setComment(String comm)
	{
		if(comm == null)
			return false;
		comment = comm;
		return true;


	}

	
	public String getComment()
	{
		return comment;
	}

	
	

	public long getId()
	{
		return id;
	}



	public String printTrace()
	{
		String str = "id: " + id + "\n" + 
					 "rating: " + rating + "\n" +
					 "comment: " + comment + "\n";
		
		return str;
		
	}

}