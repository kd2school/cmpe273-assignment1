package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.dto.*;


/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 * 
 */


@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private BookToL book;

    /**
     * @param book
     */
    public BookDto(BookToL book) {
	super();
	this.book = book;
    }

    /**
     * @return the book
     */
    public BookToL getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookToL book) {
	this.book = book;
    }
}
