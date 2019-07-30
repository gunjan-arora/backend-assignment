package com.springboot.rest.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.rest.model.Details;
import com.springboot.rest.model.TransactionFile;


@RunWith(SpringRunner.class)
@WebMvcTest(value = {CsvXmlServiceImpl.class}, secure = false)
public class CsvXmlServiceImplTest {

	@Autowired
	private CsvXmlService csvXmlService;

	
	@Test
	public void testCsvXmlServicewithCsv() {
		TransactionFile txnFile =new TransactionFile();
		Details details=new Details();
		try {
			txnFile.setFileType("csv");
			details=csvXmlService.processCsvXmlRecord(txnFile); 
		
			Assert.assertEquals(true, !(details.getMessage().isEmpty()));
		
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testCsvXmlServicewithXml() {
		TransactionFile txnFile =new TransactionFile();
		Details details=new Details();
		try {
			txnFile.setFileType("xml");
			details=csvXmlService.processCsvXmlRecord(txnFile); 
		
			Assert.assertEquals(true, !(details.getMessage().isEmpty()));
		
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testCsvXmlServicewithnvalidType() {
		TransactionFile txnFile =new TransactionFile();
		Details details=new Details();
		try {
			txnFile.setFileType("cji");
			details=csvXmlService.processCsvXmlRecord(txnFile); 
		
			Assert.assertEquals("No Faulted Records Found", (details.getMessage()));
		
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, false);
		}
	}

}
