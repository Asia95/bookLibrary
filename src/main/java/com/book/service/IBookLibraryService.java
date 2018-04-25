package com.book.service;

import java.util.List;
import java.util.Set;

import com.book.model.AuthorRating;
import com.book.model.Book;

public interface IBookLibraryService {
	public Book findBookByIsbn(String isbn);
	public List<Book> findBooks();
	public Set<AuthorRating> findAuthorsRatings();
	public List<Book> findBooksByCategory(String categoryName);
}
