package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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

public class ReviewDto extends LinksDto {

	private Review review;

    /**
     * @param book
     */
    public ReviewDto(Review review) {
	super();
	this.review = review;
    }

	
    public void setReview(Review review)
    {
    	this.review = review;
    }
    	
    
	public Review getReview()
	{
		return review;
	}
	
}
