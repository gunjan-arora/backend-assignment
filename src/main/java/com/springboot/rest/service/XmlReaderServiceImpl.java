package com.springboot.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.springboot.rest.dao.CsvXmlProcess;
import com.springboot.rest.exception.ErrorResponse;
import com.springboot.rest.model.CsvXmlReport;
import com.springboot.rest.model.CsvXmlReportList;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.Fields;

@Service
public class XmlReaderServiceImpl implements XmlReaderService {

	@Value("${app.xmlfilepath}")
	private String xmlFile;

	@Autowired
	CsvXmlProcess csvXmlProcess;

	@Override
	public void processXmlFile(Details details) {
		// TODO Auto-generated method stub
		Map<Long, String> map = new HashMap<Long, String>();
		CsvXmlReportList csvXmlReportList=null;
		Fields field=null;
		Long counter = 0L;
		ErrorResponse validMsg=new ErrorResponse();
		List<ErrorResponse> validList = new ArrayList<ErrorResponse>();

		try {
			csvXmlReportList= new CsvXmlReportList();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("record");

			csvXmlReportList.setCsvXmlReportList(new ArrayList<CsvXmlReport>());
			Element eElement = null;
			Node nNode = null;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					field = new Fields();
					csvXmlProcess.setFields(field,
							(eElement.getAttribute("reference") == null ? "" : eElement.getAttribute("reference")),
							(eElement.getElementsByTagName("description") == null ? ""
									: eElement.getElementsByTagName("description").item(0).getTextContent()),
							(eElement.getElementsByTagName("startBalance") == null ? ""
									: eElement.getElementsByTagName("startBalance").item(0).getTextContent()),
							(eElement.getElementsByTagName("mutation") == null ? ""
									: eElement.getElementsByTagName("mutation").item(0).getTextContent()),
							(eElement.getElementsByTagName("endBalance") == null ? ""
									: eElement.getElementsByTagName("endBalance").item(0).getTextContent()),
							details);
					csvXmlProcess.processRecord(field, csvXmlReportList, map, details);
					counter++;
				}
			}
			details.setFaultedRecords(csvXmlReportList);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			List<String> desc = new ArrayList<>();
			desc.add(e.getLocalizedMessage());
			validMsg.setMessage(e.getMessage());
			validMsg.setDetails(desc);

			validList.add(validMsg);
			details.setValidationMsg(validList);
		} catch (Exception ex) {
			List<String> desc = new ArrayList<>();
			desc.add(ex.getLocalizedMessage());
			validMsg.setMessage(ex.getMessage());
			validMsg.setDetails(desc);

			validList.add(validMsg);
			details.setValidationMsg(validList);
		}
	}

}
