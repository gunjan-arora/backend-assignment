package com.springboot.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CsvXmlReportList {
	
    private List<CsvXmlReport> csvXmlReportList;
    
    public List<CsvXmlReport> getCsvXmlReportList() {
        if(csvXmlReportList == null) {
        	csvXmlReportList = new ArrayList<>();
        }
        return csvXmlReportList;
    }
 
    public void setCsvXmlReportList(List<CsvXmlReport> csvXmlReportList) {
        this.csvXmlReportList = csvXmlReportList;
    }
}
