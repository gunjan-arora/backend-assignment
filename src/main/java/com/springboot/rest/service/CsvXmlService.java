package com.springboot.rest.service;

import org.springframework.stereotype.Service;

import com.springboot.rest.model.Details;
import com.springboot.rest.model.TransactionFile;


@Service
public interface CsvXmlService {
	
	public Details processCsvXmlRecord(TransactionFile txnFile);

}
