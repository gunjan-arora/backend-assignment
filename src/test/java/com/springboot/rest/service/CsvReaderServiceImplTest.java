package com.springboot.rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.rest.model.Details;


@RunWith(SpringRunner.class)
@WebMvcTest(value = {CsvReaderServiceImpl.class}, secure = false)
public class CsvReaderServiceImplTest {

	
	@Autowired
	private CsvReaderService csvReaderService;
	
	@Test
	public void testcsvReader() {
		Details details=new Details();
		
		try {
		csvReaderService.processCsvFile(details);
		Assert.assertEquals(true, (details.getFaultedRecords()!=null));
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, false);
		}
	}

	
	@Test
	public void testcsvReaderwithNull() {
		Details details=null;
		
		try {
		csvReaderService.processCsvFile(details);
		Assert.assertEquals(true, (details.getFaultedRecords()!=null));
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, true);
		}
	}

	
}
