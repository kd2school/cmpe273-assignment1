package edu.sjsu.cmpe.library.api.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.yammer.dropwizard.jersey.params.*;
import com.yammer.metrics.annotation.Timed;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dao.*;
import edu.sjsu.cmpe.library.dto.*;



/****
 * 
 * @author Ai-Phuong Le
 * CMPE 273 Assignment 1
 * Date 9/24/2013
 *
 */


@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

	private final static BookDAO bookRepository = new BookDAO();

	/**
	 * BookResource constructor
	 * 
	 * @param bookRepository
	 *            a BookRepository instance
	 */
	public BookResource() {
		
	}


	/****
	 * 	Create Book API
	 *     Resource: POST - /books
	 *     Description: Add a new book along with the author information to the library.
	 * @param request
	 * @return
	 */

	@POST
	@Timed(name = "create-book")
	public Response createBook(Book request) {
		// Store the new book in the BookRepository so that we can retrieve it.

		Response response;
		
		try {
			Book book = bookRepository.addBookList(request.getIsbn(),request);

			String location = "/books/" + book.getIsbn();
			LinksDto links = new LinksDto();
			links.addLink(new LinkDto("view-book", location, "GET"));
			links.addLink(new LinkDto("update-book", location, "PUT"));
			links.addLink(new LinkDto("delete-book", location, "DELETE"));
			links.addLink(new LinkDto("create-review", location + "/reviews", "POST"));

			response = Response.status(201).entity(links).build();
		}
		catch(Exception ex) {
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Can't create a book: " + ex.getMessage()).build();
		}

		return response;
	}


	/****
	 * 	View Book API
	 *  Resource: GET - /books/{isbn}
	 *  Description: View an existing book from the library.
	 *  
	 *  @param isbn
	 *  @return
	 */

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")
	public Response viewBook(@PathParam("isbn") LongParam isbn) {

		Response response;

		try {	
			Book book = bookRepository.searchByID(isbn.get());

			String location = "/books/" + book.getIsbn();
			BookDto bookResponse = new BookDto(new BookToL(book));
			bookResponse.addLink(new LinkDto("view-book", location, "GET"));
			bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
			bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
			location += "/reviews";
			bookResponse.addLink(new LinkDto("create-review", location, "POST"));
			if(!book.isReviewEmpty())
				bookResponse.addLink(new LinkDto("view-all-reviews", location, "GET"));

			response = Response.status(200).entity(bookResponse).build();
		}

		catch(NullPointerException ex) {
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Book isn't found: " + ex.getMessage()).build();
		}

		return response;

	}

	/****
	 *	Delete Book API
	 *	Resource: DELETE - /books/{isbn}
	 *	Description: Delete an existing book from the library.
	 *
	 * @param isbn
	 * @return
	 */

	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")
	public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) {
		Response response;

		Book book = bookRepository.removeFromBookList(isbn.get());
		if(book == null)
			response = Response.status(Response.Status.BAD_REQUEST).
			entity("Book can't be deleted").build();

		else
			response = new RootResource().getRoot();

		return response;

	}



	/****
	 *  Update Book API
	 *  Resource: PUT - /books/{isbn}?status={new-status}
	 *  Description: Update an existing book meta-data from the library. For instance, 
	 *  change the status: from “Available” to “Lost”.
	 *  
	 * @param isbn, status
	 * @return
	 */


	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")
	public Response updateBook(@PathParam("isbn") LongParam isbn, 
			@QueryParam("status") String status) 
	{
		Response response;
		
		try {
			long key = isbn.get();

			Book book = bookRepository.searchByID(key);
			book.setStatus(status);

			String location = "/books/" + key;

			LinksDto bookResponse = new LinksDto();
			bookResponse.addLink(new LinkDto("view-book", location, "GET"));
			bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
			bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
			location += "/reviews";
			bookResponse.addLink(new LinkDto("create-review", location, "POST"));
			if(!book.isReviewEmpty())
				bookResponse.addLink(new LinkDto("view-all-reviews", location, "GET"));

			response = Response.status(200).entity(bookResponse).build();

		}

		catch(Exception ex)
		{
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Status can't be updated: " + ex.getMessage()).build();
		}

		return response;
	}


	/****
	 * Create Book Review API
	 * Resource: POST - /books/{isbn}/reviews
	 * Description: Add a new review to the book. (Neither updating nor deleting reviews is 
	 * allowed to minimize the scope) 
	 * 
	 * @param isbn review
	 * @return
	 */


	@POST
	@Path("/{isbn}/reviews")
	@Timed(name = "create-review")
	public Response createReview(@PathParam("isbn") LongParam isbn, Review review) {

		long key = isbn.get();
		Response response;

		try {
			Book book = bookRepository.searchByID(key);
			System.out.println("CREATE REVIEW");
			System.out.println(review.printTrace());
			book.addReview(review);

			String location = "/books/" + key + "/reviews/" + review.getId();
			LinksDto link = new LinksDto();
			link.addLink(new LinkDto("view-review", location, "GET"));
			response = Response.status(201).entity(link).build();

		}

		catch(Exception ex)
		{
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("can't create review: " + ex.getMessage()).build();
		}

		return response;
	}


	/****
	 * View Book Review API
	 * Resource: GET - /books/{isbn}/reviews/{id}
	 * Description: View a particular review of the book.
	 * 
	 * @param isbn, id
	 * @return
	 */


	@GET
	@Path("/{isbn}/reviews/{id}")
	@Timed(name = "view-review")
	public Response viewReview(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam reviewID) 
	{
		Response response;
		long key = isbn.get();
		long id = reviewID.get();
		try {

			Book book = bookRepository.searchByID(key);
			Review review = book.searchReviewByID(id);

			ReviewDto reviewDto = new ReviewDto(review);
			String location = "/books/" + key + "/reviews/" + review.getId();
			reviewDto.addLink( new LinkDto("view-review", location, "GET"));

			response = Response.status(200).entity(reviewDto).build();
		}
		catch(Exception ex)
		{
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Can't find the review: " + ex.getMessage()).build();
		}

		return response;
	}




	/****
	 * View All Reviews API
	 * Resource: GET - /books/{isbn}/reviews
	 * Description: View all reviews of the book.
	 * 
	 * @param isbn
	 * @return
	 */


	@GET
	@Path("/{isbn}/reviews")
	@Timed(name = "view-all-reviews")
	public Response viewAllReview(@PathParam("isbn") LongParam isbn) 
	{

		Response response;
		long key = isbn.get();

		try {
			Book book = bookRepository.searchByID(key);
			List<Review> reviews = book.getReviews();

			ReviewsDto reviewsDto = new ReviewsDto(reviews);

			response = Response.status(200).entity(reviewsDto).build();
		}
		catch (Exception ex)
		{
			response =  Response.status(Response.Status.BAD_REQUEST).
					entity("Can't find reviews: " + ex.getMessage()).build();
		}

		return response;
	}





	/****
	 * View Book Author API
	 * Resource: GET - /books/{isbn}/authors/{id}
	 * Description: View a particular author of the book.
	 * 
	 * @param isbn, id
	 * @return
	 *
	 ****/	

	@GET
	@Path("/{isbn}/authors/{id}")
	@Timed(name = "view-author")
	public Response viewBookAuthor(@PathParam("isbn") LongParam isbn, @PathParam("id") LongParam authorID ) 
	{

		Response response;
		long key = isbn.get();
		long id = authorID.get();

		try {
			Book book = bookRepository.searchByID(key);
			Author author = book.searchAuthorByID(id);


			String location = "/books/" + key + "/authors/" + author.getId();
			AuthorDto authorDto = new AuthorDto(author);
			authorDto.addLink(new LinkDto("view-author", location, "GET"));
			response = Response.status(200).entity(authorDto).build();
		}
		catch(Exception ex)
		{
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Can't find author: " + ex.getMessage()).build();
		}

		return response;
	}



	/****
	 * View All Authors of the Book API
	 * Resource: GET - /books/{isbn}/authors
	 * Description: View all authors of the book.
	 * 
	 * @param isbn
	 * @return
	 *
	 ****/	

	@GET
	@Path("/{isbn}/authors")
	@Timed(name = "view-all-authors")
	public Response viewBookAuthor(@PathParam("isbn") LongParam isbn) 
	{
		Response response;
		long key = isbn.get();

		try {

			Book book = bookRepository.searchByID(key);
			List<Author> authors = book.getAuthors();

			AuthorsDto authorsDto = new AuthorsDto(authors);

			response = Response.status(200).entity(authorsDto).build();
		}
		catch(Exception ex)
		{
			response = Response.status(Response.Status.BAD_REQUEST).
					entity("Can't authors: " + ex.getMessage()).build();
		}

		return response;
	}



}

