package com.cyxtera.search.vulnerabilities.api.modelo;

import java.util.Date;

/**
 * VolnurabilityResponse POJO 
 * 
 * @author said.hernandez
 * @since 10/11/2019
 *
 */
public class VolnurabilityResponse {
	
	private String statusCode;
	private String statusMessage;
	private Date date; 
	private String idTransaction;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	} 

	
	
}
