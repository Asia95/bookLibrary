package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.model.Book;

@Repository
public interface BookLibraryRepository extends JpaRepository<Book, String>{
	
}
