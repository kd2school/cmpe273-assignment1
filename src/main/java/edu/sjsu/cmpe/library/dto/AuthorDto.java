package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.*;

/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * 
 */


public class AuthorDto extends LinksDto {

	private Author author;

    /**
     * @param book
     */
    public AuthorDto(Author author) {
	super();
	this.author = author;
    }

	
    public void setAuthor(Author author)
    {
    	this.author = author;
    }
    	
    
	public Author getAuthor()
	{
		return author;
	}
	
}
