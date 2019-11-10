package com.cyxtera.search.vulnerabilities.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Intefacez charges exposing the api method
 * 
 * @author said.hernandez
 * @since 09/11/2019
 *
 */
public interface ISearchVulnerabilitiesController {
	
	/** application log */
	public static final Logger logger = LogManager.getLogger(ISearchVulnerabilitiesController.class);
	
	/**
	 * Method that look for the URL score vulnerability  
	 */
	public String toSearchVulnerabilities(); 
	
	/**
	 * Method that return the vulnerabilities 
	 */
	public void getResultVulnerabilities(); 
	
	/**
	 * Method that generates report to Security Teams
	 */
	public void toGenerateReportVulnerabilities();

}
