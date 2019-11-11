package com.cyxtera.search.vulnerabilities.api.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityResponse;
import com.cyxtera.search.vulnerabilities.api.processor.ISearchVulnerabilitiesProcessor;


public interface  ISearchVulnerabilitiesBusiness {
	
	/** application log */
	public static final Logger logger = LogManager.getLogger(ISearchVulnerabilitiesBusiness.class);
	
	/**
	 * 
	 * @param namesURL Names of the URL in order to look for the rank 
	 */
	public VolnurabilityResponse toSearchScoreVulnerabilities(ISearchVulnerabilitiesProcessor iSearchVulnerabilitiesProcessor , VolnurabilityRequest request); 
	
	/**
	 * Method in charge to build the client message  
	 * @return VolnurabilityResponse
	 */
	public VolnurabilityResponse buildMessageResponse(String message, String code);

}
