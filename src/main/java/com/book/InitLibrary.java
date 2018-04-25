package com.book;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.book.model.Book;
import com.book.model.BookLibrary;
import com.book.repository.BookLibraryRepository;

@Component
public class InitLibrary implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private BookLibraryRepository bookRepository;

	private static final Logger logger = LoggerFactory.getLogger(InitLibrary.class);

	@Value("${file.name}")
	private String library_file;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		logger.info("getting books from file: " + library_file);
		BookLibrary library = new BookLibrary();
		createLibraryFromJson(library, library_file);
		bookRepository.saveAll(library.getBookLibraryList());
		logger.info("Saved all books");
	}

	private void createLibraryFromJson(BookLibrary library, String library_file) {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(library_file), "UTF-8"));
			JSONParser jsonParser = new JSONParser();

			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			JSONArray booksArray = (JSONArray) jsonObject.get("items");

			Iterator<?> i = booksArray.iterator();
			while (i.hasNext()) {
				JSONObject bookJson = (JSONObject) i.next();

				JSONObject bookInfo = (JSONObject) bookJson.get("volumeInfo");

				Book book = library.createBookForLibrary(bookInfo, bookJson);
				library.addToBookLibraryList(book);
			}
		} catch (IOException e) {
			logger.info("IOException when creating library");
			e.printStackTrace();
		} catch (ParseException e) {
			logger.info("ParseException when parsing json to create library");
			e.printStackTrace();
		}
	}
}
