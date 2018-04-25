package com.book.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.exception.ResourceNotFoundException;
import com.book.model.AuthorRating;
import com.book.model.Book;
import com.book.service.IBookLibraryService;

@RestController
@RequestMapping(value={"/api"})
public class BookLibraryController {
	
	@Autowired
	private IBookLibraryService bookService;
	
	@RequestMapping("/book/{isbn}")
    public Book showBookByIsbn(@PathVariable(value = "isbn") String isbn) {
		Book book = bookService.findBookByIsbn(isbn);
		if (book.isEmpty())
			throw new ResourceNotFoundException("Book", "ISBN", isbn);
		return book;
    }
	
	@RequestMapping("")
    public List<Book> showBooks() {
		return bookService.findBooks();
    }
	
	@RequestMapping("/rating")
    public Set<AuthorRating> showAuthorsRatings() {
		Set<AuthorRating> authorRatingList = bookService.findAuthorsRatings();
//		if (authorRatingList.isEmpty())
//			throw new ResourceNotFoundException("No ratings!");
		return authorRatingList;
    }
	
	@RequestMapping("/category/{categoryName}/books")
    public List<Book> showBooksByCategory(@PathVariable(value = "categoryName") String categoryName) {
		List<Book> booksByCategory = bookService.findBooksByCategory(categoryName);
//		if (booksByCategory.isEmpty())
//			throw new ResourceNotFoundException("Books", "Category", categoryName);
		return booksByCategory;
    }
}
