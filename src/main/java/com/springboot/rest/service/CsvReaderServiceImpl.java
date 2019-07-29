package com.springboot.rest.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.springboot.rest.dao.CsvXmlProcess;
import com.springboot.rest.exception.ErrorResponse;
import com.springboot.rest.model.CsvXmlReport;
import com.springboot.rest.model.CsvXmlReportList;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.Fields;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {
	
	@Value("${app.csvfilepath}")
	private String csvFile;
	
	@Autowired
	CsvXmlProcess csvXmlProcess;
	
	@Override
	public void processCsvFile(Details details) throws IOException {
		Map<Long, String> map = new HashMap<Long, String>();
		CsvXmlReportList csvXmlReportList = null;
		Fields field=null;
		CSVReader reader = null;
		Long counter = 0L;
		ErrorResponse validMsg=new ErrorResponse();
		List<ErrorResponse> validList=new ArrayList<ErrorResponse>();
		try {
			csvXmlReportList=new CsvXmlReportList();
			csvXmlReportList.setCsvXmlReportList(new ArrayList<CsvXmlReport>());
			reader = new CSVReader(new FileReader(csvFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				if (!counter.equals(0L)) {
					field = new Fields();
					csvXmlProcess.setFields(field, (line[0]==null?"":line[0]), (line[2]==null?"":line[2]), 
							(line[3]==null?"":line[3]), (line[4]==null?"":line[4]), 
							(line[5]==null?"":line[5]),
							details);
					csvXmlProcess.processRecord(field, csvXmlReportList, map, details);
				}
				counter++;
			}
			details.setFaultedRecords(csvXmlReportList);
		} catch (IOException e) {
			List<String> desc = new ArrayList<>();
			desc.add(e.getLocalizedMessage());
			validMsg.setMessage(e.getMessage());
			validMsg.setDetails(desc);
			
			validList.add(validMsg);
			details.setValidationMsg(validList);
		}finally {
			reader.close();
		}
		
	}

}
