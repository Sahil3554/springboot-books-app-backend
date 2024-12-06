package com.example.booksApp.services;

import com.example.booksApp.entity.Book;
import com.example.booksApp.repository.BookRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return  bookRepository.findAll();
    }
    public Book getBook(ObjectId bookId){
        return bookRepository.findById(bookId).orElse(null);
    }
    public Book addBook(Book book){
        return bookRepository.insert(book);
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }
    public boolean findAndUpdateBook(ObjectId bookId, Book book){
        Book currBook =getBook(bookId);
        if(currBook!=null) {
            if(book.getName()!=null && book.getName()!=""){
                currBook.setName(book.getName());
            }
            if(book.getPagesCount()!=null){
                currBook.setPagesCount(book.getPagesCount());
            }
             bookRepository.save(currBook);
            return true;
        }
        return false;
    }
    public boolean deleteBook(ObjectId bookId){
        Book book =getBook(bookId);
        if(book==null) return false;
        bookRepository.deleteById(bookId);
        return true;
    }
}
