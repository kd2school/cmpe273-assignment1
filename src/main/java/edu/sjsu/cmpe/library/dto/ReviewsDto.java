package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;
import edu.sjsu.cmpe.library.domain.Review;

/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * 
 */

public class ReviewsDto extends LinksDto {

	private List<Review> reviews = new ArrayList<Review>();

	/**
	 * @param review
	 */
	public ReviewsDto(List<Review> reviews) {
		super();
		this.reviews = reviews;
	}


	public void setReviews(List<Review> reviews)
	{
		this.reviews = reviews;
	}


	public List<Review> getReviews()
	{
		return reviews;
	}

}
