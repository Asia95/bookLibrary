package com.book.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookLibrary {

	private List<Book> listBook = new ArrayList<Book>();
	private static final Logger logger = LoggerFactory.getLogger(BookLibrary.class);

	public BookLibrary() {}
	
	public Book createBookForLibrary(JSONObject bookInfo, JSONObject bookJson) {

		Book bookObj = new Book(getIsbn(bookInfo), getValue(bookInfo, "title"), getValue(bookInfo, "subtitle"),
				getValue(bookInfo, "publisher"), getPublishedDate(bookInfo, "publishedDate"),
				getValue(bookInfo, "description"), getPageCount(bookInfo, "pageCount"),
				getNestedValue(bookInfo, "imageLinks", "thumbnail"), getValue(bookInfo, "language"),
				getValue(bookInfo, "previewLink"), getAverageRating(bookInfo, "averageRating"),
				getValueArray(bookInfo, "authors"), getValueArray(bookInfo, "categories"));

		bookObj.checkIsbn((String) bookJson.get("id"));
		return bookObj;

	}

	private String getValue(JSONObject obj, String key) {
		if (obj.containsKey(key))
			return (String) obj.get(key);
		else
			return "";
	}

	private Integer getPageCount(JSONObject obj, String key) {
		Integer pageCount = -1;
		if (obj.containsKey(key))
			pageCount = (int) (long) obj.get(key);
		return pageCount;
	}

	private Double getAverageRating(JSONObject obj, String key) {
		Double averageRating = -1.0;
		if (obj.containsKey(key))
			averageRating = (Double) obj.get(key);
		return averageRating;
	}

	private String getNestedValue(JSONObject obj, String key, String nestedKey) {
		if (obj.containsKey(key)) {
			JSONObject nestedValues = (JSONObject) obj.get(key);
			return (String) nestedValues.get(nestedKey);
		} else
			return "";
	}

	private List<String> getValueArray(JSONObject obj, String key) {
		if (obj.containsKey(key)) {
			JSONArray arrayValues = (JSONArray) obj.get(key);
			List<String> values = new ArrayList<String>();
			for (int k = 0; k < arrayValues.size(); k++) {
				values.add((String) arrayValues.get(k));
			}
			return values;
		} else
			return new ArrayList<String>();
	}

	private String getIsbn(JSONObject obj) {
		String isbn = "";
		if (obj.containsKey("industryIdentifiers")) {
			JSONArray arrayValues = (JSONArray) obj.get("industryIdentifiers");
			for (int k = 0; k < arrayValues.size(); k++) {
				JSONObject value = (JSONObject) arrayValues.get(k);
				if (value.get("type").equals("ISBN_13"))
					return isbn = (String) value.get("identifier");
			}
		}
		return isbn;
	}

	private Long getPublishedDate(JSONObject obj, String key) {
		Long unixTime = (long) -1;
		try {
			if (obj.containsKey(key)) {
				String sDate = (String) obj.get(key);
				if (sDate.length() == 10) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = dateFormat.parse(sDate + " 00:00:00");
					unixTime = (long) date.getTime() / 1000;
				} else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
					Date date = dateFormat.parse(sDate + "-01-01 00:00:00");
					unixTime = (long) date.getTime() / 1000;
				}
			}
		} catch (java.text.ParseException e) {
			logger.info("ParseException when parsing date for book publishedDate");
			e.printStackTrace();
		}

		return unixTime;
	}

	public List<Book> getBookLibraryList() {
		return listBook;
	}
	
	public void addToBookLibraryList(Book book) {
		listBook.add(book);
	}

}
