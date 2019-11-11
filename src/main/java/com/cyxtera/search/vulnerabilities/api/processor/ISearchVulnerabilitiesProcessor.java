package com.cyxtera.search.vulnerabilities.api.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cyxtera.search.vulnerabilities.api.exception.SearchVulnerabilitiesException;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;

/**
 * ISearchVulnerabilitiesProcessor in charge of searching if the URL is security  or insecurity
 * 
 * @author said.hernandez
 * @since 10/11/2019
 * @version 1.0.0
 *
 */
public interface ISearchVulnerabilitiesProcessor {
	
	/** application log */
	public static final Logger logger = LogManager.getLogger(ISearchVulnerabilitiesProcessor.class);
	
	/**
	 * searching if the URL is security  or insecurity 
	 * 
	 * @param namesURL Names of the URL in order to look for the rank 
	 */
	public void toSearchScoreVulnerabilities(VolnurabilityRequest request) throws SearchVulnerabilitiesException; 
}
