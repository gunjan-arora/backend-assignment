package com.springboot.rest.model;

import org.springframework.stereotype.Component;

@Component
public class TransactionFile {
	private String fileType;

	public String getFileType() {
		if (fileType == null)
			fileType = "";

		return fileType;
	}

	public void setFileType(String fileType) {
		if (fileType == null)
			this.fileType = "";
		else
			this.fileType = fileType;
	}

}
