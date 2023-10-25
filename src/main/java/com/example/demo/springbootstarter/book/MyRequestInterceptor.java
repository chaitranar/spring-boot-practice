package com.example.demo.springbootstarter.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyRequestInterceptor implements ClientHttpRequestInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(MyRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		logger.info("Request details");
		logger.info("URI: "+request.getURI());
		logger.info("Headers: "+ request.getHeaders());
		logger.info("Method: "+ request.getMethod());
		
		ClientHttpResponse response = execution.execute(request, body);
		MyClientHttpResponse myClientHttpResponse = new MyClientHttpResponse(response);
		
		logger.info("Response Details:");
		logger.info("status: "+ myClientHttpResponse.getStatusCode());
		//body is getting consumed here, so it throws IO exception. Hence customizing ClientHttpResponse
		//logger.info("Body: "+ getResponseBody(response.getBody()));
		logger.info("Body: "+ getResponseBody(myClientHttpResponse.getBody()));
		return myClientHttpResponse;
	}

	private String getResponseBody(InputStream responseBody) {
		StringBuilder inputStringBuilder = new StringBuilder();
		
		try(BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(responseBody,StandardCharsets.UTF_8)))
		{
			String string = bufferedReader.readLine();
			while (string!=null) {
				inputStringBuilder.append(string);
				inputStringBuilder.append('\n');
				string =bufferedReader.readLine();
			}return inputStringBuilder.toString();
		}
		catch(IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}

}
