package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.dto.*;

/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * 
 */



public class AuthorsDto extends LinksDto {

	private List<Author> authors = new ArrayList<Author>();

	/**
	 * @param book
	 */
	public AuthorsDto(List<Author> authors) {
		super();
		this.authors = authors;
	}


	public void setAuthors(List<Author> authors)
	{
		this.authors = authors;
	}


	public List<Author> getAuthors()
	{
		return authors;
	}

}
