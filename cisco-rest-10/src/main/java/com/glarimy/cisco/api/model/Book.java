package com.glarimy.cisco.api.model;

import org.springframework.data.annotation.Id;

public class Book {
	@Id
	protected int isbn;
	protected String title;

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
