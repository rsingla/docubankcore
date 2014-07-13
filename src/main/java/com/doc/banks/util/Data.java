package com.doc.banks.util;

import java.io.BufferedReader;
import java.util.Date;

public class Data {

	BufferedReader fileContent;
	Date currentDate;

	public BufferedReader getFileContent() {
		return fileContent;
	}

	public void setFileContent(BufferedReader fileContent) {
		this.fileContent = fileContent;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public String toString() {
		return "Data [fileContent=" + fileContent + ", currentDate="
				+ currentDate + "]";
	}

	public static Data builder(BufferedReader fileContent, Date currentDate) {
		Data data = new Data();
		data.setCurrentDate(currentDate);
		data.setFileContent(fileContent);

		return data;
	}

}
