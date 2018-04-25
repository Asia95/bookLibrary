package com.book.serialize;

import java.io.IOException;
import java.util.List;

import com.book.model.Book;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BookSerializer extends StdSerializer<Book> {

	protected BookSerializer(Class<Book> t) {
		super(t);
	}

	public BookSerializer() {
		this(null);
	}

	@Override
	public void serialize(Book book, JsonGenerator jgen, SerializerProvider sp)
			throws IOException, JsonGenerationException {
		jgen.writeStartObject();
		
		saveStringIfNotEmpty(jgen, "isbn", book.getIsbn());
		saveStringIfNotEmpty(jgen, "title", book.getTitle());
		saveStringIfNotEmpty(jgen, "subtitle", book.getSubtitle());
		saveStringIfNotEmpty(jgen, "publisher", book.getPublisher());
		if (book.getPublishedDate() != -1)
			jgen.writeNumberField("publishedDate", book.getPublishedDate());
		saveStringIfNotEmpty(jgen, "description", book.getDescription());
		if (book.getPageCount() != -1)
			jgen.writeNumberField("pageCount", book.getPageCount());
		saveStringIfNotEmpty(jgen, "thumbnailURl", book.getThumbnailURl());
		saveStringIfNotEmpty(jgen, "language", book.getLanguage());
		saveStringIfNotEmpty(jgen, "previewLink", book.getPreviewLink());
		if (book.getAverageRating() != -1.0)
			jgen.writeNumberField("averageRating", book.getAverageRating());
		saveArrayIfNotEmpty(jgen, "authors", book.getAuthors());
		saveArrayIfNotEmpty(jgen, "categories", book.getCategories());
		
		jgen.writeEndObject();
	}

	private void saveStringIfNotEmpty(JsonGenerator jgen, String field, String value) throws IOException {
		if (!value.isEmpty() && !value.equals(null))
			jgen.writeStringField(field, value);
	}
	
	private void saveArrayIfNotEmpty(JsonGenerator jgen, String field, List<String> value) throws IOException {
		if (!value.isEmpty()) {
			jgen.writeArrayFieldStart(field);
			for (String c : value)
				jgen.writeString(c);
			jgen.writeEndArray();
		}
	}

}
