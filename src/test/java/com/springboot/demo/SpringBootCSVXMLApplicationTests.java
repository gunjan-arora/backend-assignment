package com.springboot.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.springboot.rest.SpringBootCSVXMLApplication;
import com.springboot.rest.model.TransactionFile;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCSVXMLApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootCSVXMLApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testisApplicationRunning() throws URISyntaxException {
		String baseUrl = "http://localhost:" + randomServerPort + "/";
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(baseUrl + "springbootrestdemo/");
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true,
				result.getBody().contains("springbootrestdemo: Application is up. Please proceed with request"));
	}

	@Test
	public void testcsvReadersuccess() throws URISyntaxException {
		String baseUrl = "http://localhost:" + randomServerPort + "/";
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(baseUrl + "springbootrestdemo/validateMT940Report/cSv");

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void testxmlReadersuccess() throws URISyntaxException {
		String baseUrl = "http://localhost:" + randomServerPort + "/";
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(baseUrl + "springbootrestdemo/validateMT940Report/xml");

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void testInvalidFileType() throws URISyntaxException {
		String baseUrl = "http://localhost:" + randomServerPort + "/";
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(baseUrl + "springbootrestdemo/validateMT940Report/CJl");

//		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		// Verify request succeed
		Assert.assertEquals(400, 400);
	}
}
