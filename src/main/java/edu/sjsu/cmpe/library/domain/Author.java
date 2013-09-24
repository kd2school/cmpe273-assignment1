package edu.sjsu.cmpe.library.domain;

/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 *
 */



public class Author {
	
	private static int count = 0;
	private long id;
	private String name;		// required

	public Author()
	{
		id = ++count;   

	}

	
	// return id
	public long getId()
	{
		return id;
	}
	
	
	
	// setter name
	public void setName(String name)
	{
		this.name = name;
		
	}

	
	
	// getter name
	public String getName()
	{
		return name;

	}

	public String printTrace()
	{
		String str = "id: " + id + " " + "Name: " + name;
		
		return str;
		
	}
	
}

