package com.springboot.rest.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.rest.exception.ErrorResponse;

@Component
public class Details {
    private List<ErrorResponse> errorMsg;
    private String message;
    private CsvXmlReportList faultedRecords;
    
	public List<ErrorResponse> getValidationMsg() {
		return errorMsg;
	}
	public void setValidationMsg(List<ErrorResponse> validationMsg) {
		this.errorMsg = validationMsg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CsvXmlReportList getFaultedRecords() {
		return faultedRecords;
	}
	public void setFaultedRecords(CsvXmlReportList faultedRecords) {
		this.faultedRecords = faultedRecords;
	}
    
}
