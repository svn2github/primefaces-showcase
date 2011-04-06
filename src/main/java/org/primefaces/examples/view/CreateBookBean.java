/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.examples.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.examples.domain.Book;
import org.primefaces.examples.service.BookService;
import org.primefaces.examples.service.BookServiceImpl;

public class CreateBookBean {

	private Book book = new Book();
	
	private List<Book> books = new ArrayList<Book>();
	
	private BookService bookService = new BookServiceImpl();	
	
	public void createNew() {
		if(books.contains(book)) {
			FacesMessage msg = new FacesMessage("Dublicated", "This book has already been added");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
            books.add(book);
            bookService.saveBook(book);
            book = new Book();          //reset form
        }
	}
	
	public String reinit() {
		book = new Book();
		
		return null;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
