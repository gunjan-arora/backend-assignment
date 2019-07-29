package com.springboot.rest.service;

import org.springframework.stereotype.Service;

import com.springboot.rest.model.Details;

@Service
public interface XmlReaderService {
	public void processXmlFile(Details details);
}
