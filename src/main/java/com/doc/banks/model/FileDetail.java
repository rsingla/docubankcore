package com.doc.banks.model;

import java.util.Date;

import com.doc.banks.util.ByteConvertor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FileDetail {

	@JsonProperty("filename")
	private String filename;

	@JsonProperty("upload_date")
	private String uploadDate;

	@JsonProperty("length")
	private String length;

	@JsonProperty("content_type")
	private String contentType;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "FileDetails [filename=" + filename + ", uploadDate="
				+ uploadDate + ", length=" + length + ", contentType="
				+ contentType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result
				+ ((uploadDate == null) ? 0 : uploadDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDetail other = (FileDetail) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}

	public static FileDetail builder(String filename, Date uploadDate,
			Long length, String contentType) {

		FileDetail fileDetail = new FileDetail();
		fileDetail.setContentType(contentType);
		fileDetail.setFilename(filename);
		if (length != null) {
			fileDetail.setLength(ByteConvertor.byteValue(length));
		}
		fileDetail.setUploadDate(uploadDate.toString());

		return fileDetail;

	}

}
