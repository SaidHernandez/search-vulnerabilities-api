package com.cyxtera.search.vulnerabilities.api.processor;

import java.util.List;

import com.cyxtera.search.vulnerabilities.api.modelo.VulnerabilityBD;

/**
 * IReturnVulnerabilities 
 * 
 * @author said.hernandez
 * @since 10/11/2019
 * @version 1.0.0
 *
 */
public interface IVulnerabilitiesProcessor {
	
	/**
	 * getResultVulnerabilities 
	 * 
	 * @param costumerName
	 * @return List VulnerabilityBD
	 */
	public List<VulnerabilityBD> getResultVulnerabilities(String costumerName); 

}
