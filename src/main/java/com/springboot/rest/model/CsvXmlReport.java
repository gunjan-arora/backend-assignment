package com.springboot.rest.model;

import org.springframework.stereotype.Component;

@Component
public class CsvXmlReport {

	private Long transactionReference;
	private String description;
	
	public Long getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(Long transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
