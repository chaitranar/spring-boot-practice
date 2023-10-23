package com.example.demo.springbootstarter.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookAccessService {

	@Autowired
	private RestTemplate restTemplate;
	static String baseUrl = "http://localhost:8080";
	
	public String getBookDetails(HttpEntity<Object> requestEntity, Integer id) {
		
		//ResponseEntity<String> responseEntity= restTemplate.exchange(baseUrl + "/bookById/" + id, HttpMethod.GET, requestEntity, String.class);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/bookById/" + id, String.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		System.out.println("status code:"+ statusCode);
		String book = responseEntity.getBody();
		System.out.println("response Body="+ book);
		return book;
		
	}

	public List<Object> getAllBooksFromLib(HttpEntity<Object> requestEntity) {
		ResponseEntity<List> responseEntity = restTemplate.exchange("/getBooks", HttpMethod.GET,requestEntity, List.class);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		System.out.println("status code:"+ httpStatus);
		List<Object> book = responseEntity.getBody();
		System.out.println("response Body="+ book);
		return book;
	}

	public String addBookToLibrary(HttpEntity<Object> requestEntity) {
		//String url = baseUrl + "/addBooks" ;
		//ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/addBooks", HttpMethod.POST, requestEntity, String.class);
		//URI uri = restTemplate.postForLocation(url, requestEntity,String.class);
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("/addBooks", requestEntity, String.class);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		System.out.println("status code:"+ httpStatus);
		String book = responseEntity.getBody();
		System.out.println("response Body="+ book);
		return book;
	}

	public void removeBookFromLib(HttpEntity<Object> requestEntity, Integer id) {
		String url = baseUrl + "/removeBook/"+ id ;
		//ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + "/removeBook/"+ id, HttpMethod.DELETE, requestEntity, String.class);
		restTemplate.delete(url);
		System.out.println("Book deleted..");
	}

	public String updateBookFromLib(HttpEntity<Object> requestEntity) {
		ResponseEntity<String> responseEntity = restTemplate.exchange("/updateBook", HttpMethod.PUT, requestEntity, String.class);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		System.out.println("status code:"+ httpStatus);
		String book = responseEntity.getBody();
		System.out.println("response Body="+ book);
		return book;
		
	}
	
}
