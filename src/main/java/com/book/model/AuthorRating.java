package com.book.model;

import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "ratings" })
public class AuthorRating implements Comparable<AuthorRating>{
	private String author;
	private List<Double> ratings;
	private Double averageRating;
	
	public AuthorRating(String name, List<Double> ratings) {
		this.author = name;
		this.ratings = ratings;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String name) {
		this.author = name;
	}

	public List<Double> getRatings() {
		return ratings;
	}

	public void setRatings(List<Double> ratings) {
		this.ratings = ratings;
	}
	
	public void addToRatings(Double rating) {
		this.ratings.add(rating);
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double rating) {
		this.averageRating = rating;
	}
	
	public void calculateRating() {
		this.averageRating = ratings.stream().mapToDouble(val -> val).average().getAsDouble();
	}

	@Override
	public int compareTo(AuthorRating ar) {
		return Comparator.comparing(AuthorRating::getAverageRating).reversed()
				.thenComparing(AuthorRating::getAuthor)
				.compare(this,  ar);
	}

}
