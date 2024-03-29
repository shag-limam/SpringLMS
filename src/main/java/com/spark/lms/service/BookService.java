package com.spark.lms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.common.Constants;
import com.spark.lms.model.Book;
import com.spark.lms.model.Category;
import com.spark.lms.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private IssuedBookService issuedBookService;
	
	public Long getTotalCount() {
		return bookRepository.count();
	}
	
	public Long getTotalIssuedBooks() {
		return bookRepository.countByStatus(Constants.BOOK_STATUS_ISSUED);
	}
	
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
	
	public Book get(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book getByTag(String tag) {
		return bookRepository.findByTag(tag);
	}
	
	public List<Book> get(List<Long> ids) {
		return bookRepository.findAllById(ids);
	}
	
	public List<Book> getByCategory(Category category) {
		return bookRepository.findByCategory(category);
	}
	
	public List<Book> geAvailabletByCategory(Category category) {
		return bookRepository.findByCategoryAndStatus(category, Constants.BOOK_STATUS_AVAILABLE);
	}
	
	public Book addNew(Book book) {
		book.setCreateDate(new Date());
		book.setStatus( Constants.BOOK_STATUS_AVAILABLE );
		return bookRepository.save(book);
	}
	public Book update(Book book) {
		// Vérifiez si le livre existe déjà dans la base de données
		Optional<Book> existingBook = bookRepository.findById(book.getId());
		if (existingBook.isPresent()) {
			// Mettez à jour les champs du livre existant avec les nouvelles valeurs
			Book updatedBook = existingBook.get();
			updatedBook.setTitle(book.getTitle());
			updatedBook.setAuthors(book.getAuthors());
			updatedBook.setPublisher(book.getPublisher());
			updatedBook.setIsbn(book.getIsbn());
			// Vous pouvez ajouter d'autres champs à mettre à jour ici

			// Enregistrez le livre mis à jour dans la base de données
			return bookRepository.save(updatedBook);
		} else {
			// Si le livre n'existe pas, vous pouvez choisir de renvoyer null ou de lever une exception
			return null;
			// Ou
			// throw new EntityNotFoundException("Book not found with id: " + book.getId());
		}
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public void delete(Book book) {
		bookRepository.delete(book);
	}
	
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	public boolean hasUsage(Book book) {
		return issuedBookService.getCountByBook(book)>0;
	}
}
