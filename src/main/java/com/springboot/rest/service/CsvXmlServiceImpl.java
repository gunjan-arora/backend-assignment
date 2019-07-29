package com.springboot.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.rest.exception.ErrorResponse;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.TransactionFile;

@Service
public class CsvXmlServiceImpl implements CsvXmlService {


	@Autowired
	private CsvReaderService csvReaderService;

	@Autowired
	private XmlReaderService xmlReaderService;

	@Override
	public Details processCsvXmlRecord(TransactionFile txnFile) {
		Details details=new Details();
		ErrorResponse validMsg = new ErrorResponse();
		List<ErrorResponse> validList = new ArrayList<ErrorResponse>();
		try {
			if ("csv".equalsIgnoreCase(txnFile.getFileType())) {
				csvReaderService.processCsvFile(details);

			} else if ("xml".equalsIgnoreCase(txnFile.getFileType())) {
				xmlReaderService.processXmlFile(details);
			}

			if (details.getFaultedRecords()!=null && !details.getFaultedRecords().getCsvXmlReportList().isEmpty()) {
				details.setMessage("Format Processed: " + txnFile.getFileType().toUpperCase() + ". \n\rThere are total "
						+ details.getFaultedRecords().getCsvXmlReportList().size() + " faulted Records.");
			} else {
				details.setMessage("No Faulted Records Found");
			}
		} catch (Exception e) {
			List<String> desc = new ArrayList<>();
			desc.add(e.getLocalizedMessage());
			validMsg.setMessage(e.getMessage());
			validMsg.setDetails(desc);

			validList.add(validMsg);
			details.setValidationMsg(validList);
		}
		return details;
	}

}
