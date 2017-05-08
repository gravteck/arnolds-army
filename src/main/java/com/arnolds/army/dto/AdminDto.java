package com.arnolds.army.dto;

import java.util.List;

import com.arnolds.army.model.ReportingField;

public class AdminDto {

	private String title;

	private String addPath;

	private String deletePath;

	private List<String> headers;

	List<List<ReportingField>> records;

	public AdminDto() {
	};

	public AdminDto(String title, String addPath, String deletePath, List<String> headers,
			List<List<ReportingField>> records) {
		super();
		this.title = title;
		this.addPath = addPath;
		this.deletePath = deletePath;
		this.headers = headers;
		this.records = records;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the addPath
	 */
	public String getAddPath() {
		return addPath;
	}

	/**
	 * @param addPath the addPath to set
	 */
	public void setAddPath(String addPath) {
		this.addPath = addPath;
	}

	/**
	 * @return the deletePath
	 */
	public String getDeletePath() {
		return deletePath;
	}

	/**
	 * @param deletePath the deletePath to set
	 */
	public void setDeletePath(String deletePath) {
		this.deletePath = deletePath;
	}

	/**
	 * @return the headers
	 */
	public List<String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	/**
	 * @return the records
	 */
	public List<List<ReportingField>> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(List<List<ReportingField>> records) {
		this.records = records;
	}
}
