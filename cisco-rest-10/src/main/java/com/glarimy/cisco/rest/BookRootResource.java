package com.glarimy.cisco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.glarimy.cisco.api.Library;
import com.glarimy.cisco.api.model.Book;

@RestController
public class BookRootResource {
	@Autowired
	private Library library;

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Book> add(@RequestBody Book book, UriComponentsBuilder builder) {
		library.add(book);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/book/{id}").buildAndExpand(book.getIsbn()).toUri());
		return new ResponseEntity<Book>(book, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET)
	public ResponseEntity<Book> add(@PathVariable("isbn") int isbn) {
		Book book = library.find(isbn);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
}
