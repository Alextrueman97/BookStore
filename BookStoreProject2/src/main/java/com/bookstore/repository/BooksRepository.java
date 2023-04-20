package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

	@Query(value = "SELECT * FROM books", nativeQuery = true)
	public List<Books> showAllBooks(Books books);

	@Query(value = "SELECT new Books(b.bookId, b.bookTitle, b.bookAuthor, b.bookPrice, b.bookDescription) FROM Books b WHERE b.bookId = :bookId")
	public Books showBookInfo(@Param("bookId") int bookId);
}
