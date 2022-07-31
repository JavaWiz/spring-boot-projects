package com.javawiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.javawiz.entity.Book;
import com.javawiz.exception.BookNotFoundException;
import com.javawiz.exception.BookUnSupportedFieldPatchException;
import com.javawiz.repository.BookRepository;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

	@Autowired
	private BookRepository repository;

	@GetMapping("/books/{id}")
	public Book findOne(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
	}

	@GetMapping("/books")
	public List<Book> findAll() {
		return repository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/books")
	public Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}

	// Save or update
	@PutMapping("/books/{id}")
	public Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
		return repository.findById(id).map(x -> {
			x.setName(newBook.getName());
			x.setAuthor(newBook.getAuthor());
			x.setPrice(newBook.getPrice());
			return repository.save(x);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
	}

	// update author only
	@PatchMapping("/books/{id}")
	public Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
		return repository.findById(id).map(x -> {
			String author = update.get("author");
			if (StringUtils.hasText(author)) {
				x.setAuthor(author);
				// better create a custom method to update a value = :newValue where id = :id
				return repository.save(x);
			} else {
				throw new BookUnSupportedFieldPatchException(update.keySet());
			}
		}).orElseGet(() -> {
			throw new BookNotFoundException(id);
		});
	}

	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	}
}