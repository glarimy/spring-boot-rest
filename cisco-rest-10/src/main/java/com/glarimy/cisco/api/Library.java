package com.glarimy.cisco.api;

import com.glarimy.cisco.api.exception.BookNotFoundException;
import com.glarimy.cisco.api.exception.DuplicateBookException;
import com.glarimy.cisco.api.exception.InvalidBookException;
import com.glarimy.cisco.api.exception.LibraryException;
import com.glarimy.cisco.api.model.Book;

public interface Library {
	public void add(Book book) throws InvalidBookException, DuplicateBookException, LibraryException;

	public Book find(int isbn) throws BookNotFoundException, LibraryException;
}