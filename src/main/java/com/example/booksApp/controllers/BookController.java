package com.example.booksApp.controllers;

import com.example.booksApp.entity.Book;
import com.example.booksApp.entity.APIResponse;
import com.example.booksApp.services.BookService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<APIResponse<List<Book>>> getAll(){
        APIResponse<List<Book>> resp = new APIResponse<>();
        try {
            var books = bookService.getAllBooks();
            resp.setMessage("Data Fetched Successfully!");
            resp.setData(books);
            return ResponseEntity.status(200).body(resp);
        }catch(Exception e){
            System.out.println(e);
            resp.setError("Internal Server Error!");
            return ResponseEntity.status(500).body(resp);
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<APIResponse<Book>> getBook(@PathVariable ObjectId bookId){
        APIResponse<Book> resp = new APIResponse<>();
        try {
            Book book = bookService.getBook(bookId);
            if (book == null) {
                resp.setError("Book Not Found!");
                return ResponseEntity.status(400).body(resp);
            }
            resp.setData(book);
            return ResponseEntity.status(200).body(resp);
        }catch(Exception e){
            System.out.println(e);
            resp.setError("Internal Server Error!");
            return ResponseEntity.status(500).body(resp);
        }

    }

    @PostMapping
    public ResponseEntity<APIResponse<Book>> addBook(@RequestBody Book book){
        APIResponse<Book> resp = new APIResponse<>();
        try {
            resp.setData(bookService.addBook(book));
            resp.setMessage("Book Created Successfully!");
            return ResponseEntity.status(201).body(resp);
        }catch(Exception e){
            System.out.println(e);
            resp.setError("Internal Server Error!");
            return ResponseEntity.status(500).body(resp);
        }
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<APIResponse<Void>> updateBook(@RequestBody Book book, @PathVariable ObjectId bookId){
        APIResponse<Void> resp = new APIResponse<>();
        try {
            boolean updateSuccess = bookService.findAndUpdateBook(bookId, book);
            if (!updateSuccess){
                resp.setError("Book With Id not found!");
                return ResponseEntity.status(400).body(resp);
            }
            resp.setMessage("Updated SuccessFully!");
            return ResponseEntity.status(200).body(resp);
        }catch(Exception e){
            System.out.println(e);
            resp.setError("Internal Server Error!");
            return ResponseEntity.status(500).body(resp);
        }
    }
    @DeleteMapping("/{bookId}")
    public ResponseEntity<APIResponse<Void>> deleteBook(@PathVariable ObjectId bookId){
        APIResponse<Void> resp = new APIResponse<>();
        try {
            boolean success = bookService.deleteBook(bookId);
            if (!success) {
                resp.setError("Failed to Delete Book!");
                return ResponseEntity.status(400).body(resp);
            }
            resp.setMessage("Deleted SuccessFully!");
            return ResponseEntity.status(200).body(resp);
        }catch(Exception e){
            System.out.println(e);
            resp.setError("Internal Server Error!");
            return ResponseEntity.status(500).body(resp);
        }
    }
}
