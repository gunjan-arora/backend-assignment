package com.springboot.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.exception.ErrorResponse;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.TransactionFile;
import com.springboot.rest.service.CsvXmlService;

@RestController
@RequestMapping(path = "/")
public class CsvXmlRestController {

	@Autowired
	private CsvXmlService csvXmlService;

	@GetMapping(path = "/", produces = "text/plain")
	public String getWelcomePage() {
		return "springbootrestdemo: Application is up. Please proceed with request";
	}

	@GetMapping(path = "/springbootrestdemo", produces = "text/plain")
	public String getspringbootrestdemoPage() {
		return "springbootrestdemo: Application is up. Please proceed with request";
	}

	@GetMapping(path = "/springbootrestdemo/validateMT940Report/{txnFile}", produces = "application/json")
	public ResponseEntity<Object> validateMT940ReportM(@PathVariable String txnFile) {
		TransactionFile file = new TransactionFile();
		file.setFileType(txnFile);
		ResponseEntity<Object> result = null;

		if ("csv".equalsIgnoreCase(file.getFileType()) || "xml".equalsIgnoreCase((file.getFileType()))) {
			Details record = csvXmlService.processCsvXmlRecord(file);
			if(record.getValidationMsg()!=null && !record.getValidationMsg().isEmpty()) {
				result = new ResponseEntity(record, HttpStatus.CONFLICT);
			}else {
				result = new ResponseEntity(record, HttpStatus.OK);
			}
		} else {
			List<String> list=new ArrayList<>();
			list.add("File Type is invalid, it should be either XML or CSV");
			ErrorResponse error = new ErrorResponse("Bad Request",list );
			result = new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		}
		return result;
	}

	@PostMapping(path = "/validateMT940Report", consumes = "application/json", produces = "application/json")
	public Details validateMT940ReportM(@RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
			@RequestHeader(name = "X-COM-LOCATION", defaultValue = "INDIA") String headerLocation,
			@RequestBody TransactionFile txnFile) throws Exception {
		Details result = null;
		result = csvXmlService.processCsvXmlRecord(txnFile);

		return result;
	}
}
