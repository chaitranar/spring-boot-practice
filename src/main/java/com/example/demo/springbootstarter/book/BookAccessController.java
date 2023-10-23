package com.example.demo.springbootstarter.book;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookAccessController {
	
	@Autowired
	private BookAccessService bookAccessService;
	HttpHeaders headers = new HttpHeaders();
	
	@GetMapping("/getBookFromLibrary/{id}")
	public String getBookFromLib(@PathVariable Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		
		return bookAccessService.getBookDetails(requestEntity,id);
	}
	
	@GetMapping("/getAllBooksFromLibrary")
	public List<Object> getAllBooksFromLibrary(){
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		return bookAccessService.getAllBooksFromLib(requestEntity);
	}
	
	
	@PostMapping("/addNewBook")
	public String addBooksToLibrary() {
		headers.setContentType(MediaType.APPLICATION_JSON);
		Book book = new Book();
		book.setDescription("Learning Spring boots");
		book.setName("Spring boots");
		HttpEntity<Object> requestEntity = new HttpEntity<>(book,headers);
		return bookAccessService.addBookToLibrary(requestEntity);
	}
	
	@DeleteMapping("/removeBookFromLib/{id}")
	public void removeBookFromLib(@PathVariable Integer id) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		bookAccessService.removeBookFromLib(requestEntity,id);
	}
	
	@PutMapping("/updateBookFromLib")
	public String updateBookFromLib(@RequestBody Book book) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<>(book, headers);
		return bookAccessService.updateBookFromLib(requestEntity);
	}
	
	
}
