package com.springboot.rest.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springboot.rest.exception.ErrorResponse;
import com.springboot.rest.model.CsvXmlReport;
import com.springboot.rest.model.CsvXmlReportList;
import com.springboot.rest.model.Details;
import com.springboot.rest.model.Fields;

@Repository
public class CsvXmlProcessDAO implements CsvXmlProcess {
	
	@Override
	public void processRecord(Fields field, CsvXmlReportList list, Map<Long, String> map, Details details) {
		// TODO Auto-generated method stub
		CsvXmlReport faultRecord = new CsvXmlReport();
		boolean isCalcWrong = false;
		ErrorResponse validMsg=new ErrorResponse();
		List<ErrorResponse> validList=new ArrayList<ErrorResponse>();
		try {
			faultRecord.setTransactionReference(field.getTransactionReference());
			faultRecord.setDescription(field.getDescription());

			if (!((field.getStartBalance().add(field.getMutation())).equals(field.getEndBalance()))) {
				isCalcWrong = true;
			}

			if (map.containsKey(field.getTransactionReference())) {
				list.getCsvXmlReportList().add(faultRecord);
			} else if (isCalcWrong) {
				list.getCsvXmlReportList().add(faultRecord);
			}
			
			map.put(field.getTransactionReference(), field.getDescription());

		} catch (Exception e) {
			List<String> desc = new ArrayList<>();
			desc.add(e.getLocalizedMessage());
			validMsg.setMessage(e.getMessage());
			validMsg.setDetails(desc);
			
			validList.add(validMsg);
			details.setValidationMsg(validList);
		}
	}
	
	@Override
	public boolean setFields(Fields field, String reference, String Desc, String startBal,
			String mutation, String endBal, Details details) {
		boolean result = true;
		ErrorResponse validMsg=new ErrorResponse();
		List<ErrorResponse> validList=new ArrayList<ErrorResponse>();
		try {
			field.setTransactionReference(Long.valueOf(("".equals(reference)) ? "0" : reference));
			field.setDescription(Desc);
			field.setStartBalance(new BigDecimal("".equals(startBal) ? "0" : startBal.replaceAll(",", "")));
			field.setMutation(new BigDecimal("".equals(mutation) ? "0" : mutation.replaceAll(",", "")));
			field.setEndBalance(new BigDecimal("".equals(endBal) ? "0" : endBal.replaceAll(",", "")));
		} catch (NumberFormatException e) {
			List<String> desc = new ArrayList<>();
			desc.add(e.getLocalizedMessage());
			validMsg.setMessage(e.getMessage());
			validMsg.setDetails(desc);
			
			validList.add(validMsg);
			details.setValidationMsg(validList);
		}
		return result;
	}

}
