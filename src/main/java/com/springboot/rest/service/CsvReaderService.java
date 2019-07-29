package com.springboot.rest.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.springboot.rest.model.Details;

@Service
public interface CsvReaderService {
	public void processCsvFile(Details details) throws IOException;
}
