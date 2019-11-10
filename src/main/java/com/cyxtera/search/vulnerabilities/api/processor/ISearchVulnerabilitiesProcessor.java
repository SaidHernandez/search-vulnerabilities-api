package com.cyxtera.search.vulnerabilities.api.processor;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ISearchVulnerabilitiesProcessor {
	
	/** application log */
	public static final Logger logger = LogManager.getLogger(ISearchVulnerabilitiesProcessor.class);
	
	/**
	 * 
	 * @param namesURL Names of the URL in order to look for the rank 
	 */
	public void toSearchScoreVulnerabilities(List<String> namesURL); 
}
