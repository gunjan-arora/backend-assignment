package com.springboot.rest.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springboot.rest.model.CsvXmlReportList;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.Fields;

@Repository
public interface CsvXmlProcess {
	public void processRecord(Fields field, CsvXmlReportList list, Map<Long, String> map, Details details);
	public boolean setFields(Fields field, String reference, String Desc, String startBal, String mutation, String endBal, Details details);
}
