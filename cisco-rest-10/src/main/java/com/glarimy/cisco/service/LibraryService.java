package com.glarimy.cisco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glarimy.cisco.api.Library;
import com.glarimy.cisco.api.exception.BookNotFoundException;
import com.glarimy.cisco.api.exception.DuplicateBookException;
import com.glarimy.cisco.api.exception.InvalidBookException;
import com.glarimy.cisco.api.exception.LibraryException;
import com.glarimy.cisco.api.model.Book;
import com.glarimy.cisco.data.BookRepository;

@Service
public class LibraryService implements Library {
	@Autowired
	private BookRepository repo;

	@Override
	public void add(Book book) throws InvalidBookException, DuplicateBookException, LibraryException {
		if (book == null || book.getIsbn() < 0 || book.getTitle() == null || book.getTitle().trim().length() == 0)
			throw new InvalidBookException();
		if (repo.findById(book.getIsbn()).orElse(null) != null)
			throw new DuplicateBookException();
		repo.save(book);
	}

	@Override
	public Book find(int isbn) throws BookNotFoundException, LibraryException {
		Book book = repo.findById(isbn).orElse(null);
		if (book == null)
			throw new BookNotFoundException();
		return book;
	}

}
