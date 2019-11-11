package com.cyxtera.search.vulnerabilities.api.modelo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class VolnurabilityRequest {

	@NotEmpty
	private String idTrasantion;
	@NotEmpty
	private Date dateTransantion;
	@NotEmpty
	private String costumerName;
	@NotEmpty
	private List<String> url;
	
	public VolnurabilityRequest() {
		super();
	}

	public String getIdTrasantion() {
		return idTrasantion;
	}

	public void setIdTrasantion(String idTrasantion) {
		this.idTrasantion = idTrasantion;
	}

	public Date getDateTransantion() {
		return dateTransantion;
	}

	public void setDateTransantion(Date dateTransantion) {
		this.dateTransantion = dateTransantion;
	}

	public String getCostumerName() {
		return costumerName;
	}

	public void setCostumerName(String costumerName) {
		this.costumerName = costumerName;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

}
