package com.springboot.rest.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.rest.model.CsvXmlReportList;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.Fields;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CsvXmlProcessDAO.class, secure = false)
public class CsvXmlProcessDAOTest {
	
	@Autowired
	CsvXmlProcess csvXmlProcess;
	
		
	@Test
	public void testprocessRecordWithCorrectData() {
		List<Fields> list= createDate();
		CsvXmlReportList clist=new CsvXmlReportList();
		Map<Long, String> map = new HashMap<Long, String>();
		Details details=new Details();
		for(Fields f:list) {
		csvXmlProcess.processRecord(f, clist, map, details);
		}
		Assert.assertEquals(true, (details.getFaultedRecords()==null));
	}
	
	
	@Test
	public void testprocessRecordWithWrongData() {
		List<Fields> list= createwrongDate();
		CsvXmlReportList clist=new CsvXmlReportList();
		Map<Long, String> map = new HashMap<Long, String>();
		Details details=new Details();
		for(Fields f:list) {
		csvXmlProcess.processRecord(f, clist, map, details);
		}
		Assert.assertEquals(true, !(clist.getCsvXmlReportList().isEmpty()));
	}
	
	private List<Fields> createDate(){
		List<Fields> list= new ArrayList<>();
		Fields field= null;
		
		String reference="117766",Desc="Flowers for the Rik TheuB",startBal="45.5",mutation="48.1",endBal="93.7";
		
		for(int i=1;i<11;i++) {
		field= new Fields();
		reference=reference+i;
		Desc=Desc+i;
		startBal=startBal+i;
		mutation=mutation+i;
		endBal=endBal+i;
		
		field.setTransactionReference(Long.valueOf(("".equals(reference)) ? "0" : reference));
		field.setDescription(Desc);
		field.setStartBalance(new BigDecimal("".equals(startBal) ? "0" : startBal.replaceAll(",", "")));
		field.setMutation(new BigDecimal("".equals(mutation) ? "0" : mutation.replaceAll(",", "")));
		field.setEndBalance(new BigDecimal("".equals(endBal) ? "0" : endBal.replaceAll(",", "")));
		
		list.add(field);
		}
		return list;
	}
	
	private List<Fields> createwrongDate(){
		List<Fields> list= new ArrayList<>();
		Fields field= null;
		
		String reference="117766",Desc="Flowers for the Rik TheuB",startBal="45.5",mutation="48.1",endBal="93.7";
		
		for(int i=1;i<11;i++) {
		field= new Fields();
		reference=reference+i;
		Desc=Desc+i;
		startBal=startBal+(i+2);
		mutation=mutation+i+(i+4);
		endBal=endBal+i;
		
		field.setTransactionReference(Long.valueOf(("".equals(reference)) ? "0" : reference));
		field.setDescription(Desc);
		field.setStartBalance(new BigDecimal("".equals(startBal) ? "0" : startBal.replaceAll(",", "")));
		field.setMutation(new BigDecimal("".equals(mutation) ? "0" : mutation.replaceAll(",", "")));
		field.setEndBalance(new BigDecimal("".equals(endBal) ? "0" : endBal.replaceAll(",", "")));
		
		list.add(field);
		}
		return list;
	}

}
