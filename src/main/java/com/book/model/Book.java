package com.book.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.book.serialize.BookSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using=BookSerializer.class)
@Entity
@Table(name="books")
public class Book {
	
	@Id
	private String isbn;
	private String title;
	private String subtitle;
	private String publisher;
	private Long publishedDate = (long) -1;
	@Column(columnDefinition = "VARCHAR", length = 65535)
	private String description;
	private Integer pageCount = -1;
	private String thumbnailURl;
	private String language;
	private String previewLink;
	private Double averageRating = -1.0;
	@ElementCollection
	private List<String> authors;
	@ElementCollection
	private List<String> categories;
	
	public Book() {
		this.isbn = "";
	}

	public boolean isEmpty() {
		return this.isbn.equals(null) || this.isbn.isEmpty();
	}
	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", subtitle=" + subtitle + ", publisher=" + publisher
				+ ", publishedDate=" + publishedDate + ", description=" + description + ", pageCount=" + pageCount
				+ ", thumbnailURl=" + thumbnailURl + ", language=" + language + ", previewLink=" + previewLink
				+ ", averageRating=" + averageRating + ", authors=" + authors + ", categories=" + categories + "]";
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public long getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(long publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getThumbnailURl() {
		return thumbnailURl;
	}

	public void setThumbnailURl(String thumbnailURl) {
		this.thumbnailURl = thumbnailURl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPreviewLink() {
		return previewLink;
	}

	public void setPreviewLink(String previewLink) {
		this.previewLink = previewLink;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public Book(String isbn, String title, String subtitle, String publisher, Long publishedDate, String description,
			Integer pageCount, String thumbnailURl, String language, String previewLink, Double averageRating,
			List<String> authors, List<String> categories) {
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.description = description;
		this.pageCount = pageCount;
		this.thumbnailURl = thumbnailURl;
		this.language = language;
		this.previewLink = previewLink;
		this.averageRating = averageRating;
		this.authors = authors;
		this.categories = categories;
	}

	public void checkIsbn(String bookId) {
		if (this.isbn.isEmpty() || this.isbn.equals(null))
	    	this.isbn = bookId;		
	}	

}
