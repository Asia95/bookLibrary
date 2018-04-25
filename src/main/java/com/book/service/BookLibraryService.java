package com.book.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.model.AuthorRating;
import com.book.model.Book;
import com.book.repository.BookLibraryRepository;

@Service
@Transactional
public class BookLibraryService implements IBookLibraryService {

	@Autowired
	private BookLibraryRepository bookRepository;
	
	@Override
	public Book findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElse(new Book());
		return book;
		//return bookRepository.findById(isbn)
				//.orElseThrow(() -> new ResourceNotFoundException("Book", "ISBN", isbn));
	}
	@Override
	public List<Book> findBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Set<AuthorRating> findAuthorsRatings() {
		List<Book> allBooks = bookRepository.findAll();		
		Map<String, List<Double>> authorRating = new HashMap<String, List<Double>>();
		
		for (Book book : allBooks) {		
			if (!book.getAuthors().equals(null) && !book.getAuthors().isEmpty()
					&& book.getAverageRating() != -1.0) {
				
				for (String author : book.getAuthors()) {				
					if (authorRating.containsKey(author)) {
						authorRating.get(author).add(book.getAverageRating());
					}
					else {
						List<Double> bookRatings = new ArrayList<Double>();
						bookRatings.add(book.getAverageRating());
						authorRating.put(author, bookRatings);
					}						
				}				
			}
		}
		Set<AuthorRating> authorRatingList = new TreeSet<AuthorRating>();
		authorRating.forEach((k, v) -> {
			AuthorRating authorObj = new AuthorRating(k, v);
			authorObj.calculateRating();
			authorRatingList.add(authorObj);
		});
		return authorRatingList;
	}

	@Override
	public List<Book> findBooksByCategory(String categoryName) {
		List<Book> books = bookRepository.findAll();
		List<Book> booksByCategory = books.stream().filter(
				b -> b.getCategories().contains(categoryName)).collect(Collectors.toList());
		return booksByCategory;
	}

}
