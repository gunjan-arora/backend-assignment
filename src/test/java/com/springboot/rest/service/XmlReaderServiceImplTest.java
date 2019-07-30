package com.springboot.rest.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.rest.model.Details;


@RunWith(SpringRunner.class)
@WebMvcTest(value = {XmlReaderServiceImpl.class}, secure = false)
public class XmlReaderServiceImplTest {

	@Autowired
	private XmlReaderService xmlReaderService;
	
	@Test
	public void testxmlReader() {
		Details details=new Details();
		
		try {
			xmlReaderService.processXmlFile(details);
		Assert.assertEquals(true, (details.getFaultedRecords()!=null));
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, false);
		}
	}

	
	@Test
	public void testxmlReaderwithNull() {
		Details details=null;
		
		try {
			xmlReaderService.processXmlFile(details);
		Assert.assertEquals(true, (details.getFaultedRecords()!=null));
		}catch (Exception e) {
			// TODO: handle exception
			Assert.assertEquals(true, true);
		}
	}

}
