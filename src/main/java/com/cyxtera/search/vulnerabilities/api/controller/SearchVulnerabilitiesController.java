package com.cyxtera.search.vulnerabilities.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyxtera.search.vulnerabilities.api.business.ISearchVulnerabilitiesBusiness;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityResponse;
import com.cyxtera.search.vulnerabilities.api.modelo.VulnerabilityBD;
import com.cyxtera.search.vulnerabilities.api.processor.ISearchVulnerabilitiesProcessor;
import com.cyxtera.search.vulnerabilities.api.processor.IVulnerabilitiesProcessor;

@Controller
@RequestMapping(value = "/app")
public class SearchVulnerabilitiesController implements ISearchVulnerabilitiesController {
    
	@Autowired
	ISearchVulnerabilitiesBusiness iSearchVulnerabilitiesBusiness; 
	
	@Autowired
	ISearchVulnerabilitiesProcessor iSearchVulnerabilitiesProcessor; 
	
	@Autowired
	IVulnerabilitiesProcessor iVulnerabilitiesProcessor; 
	
	@Override
	@RequestMapping(value={"/v1/vulnerability"},  produces="application/json", consumes = "application/json", method = RequestMethod.POST)
	public @ResponseBody VolnurabilityResponse toSearchVulnerabilities(@RequestBody VolnurabilityRequest request) {
		logger.info("Init the searchVulnerabilities method");
		return iSearchVulnerabilitiesBusiness.toSearchScoreVulnerabilities(iSearchVulnerabilitiesProcessor, request);
	}

	@Override
	@RequestMapping(value={"/v1/vulnerability"},  produces="application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<VulnerabilityBD> getResultVulnerabilities(@RequestParam(name = "costumerName")   String costumerName) {
		return iSearchVulnerabilitiesBusiness.getResultVulnerabilities(iVulnerabilitiesProcessor, costumerName);
	}

	@Override
	public void toGenerateReportVulnerabilities() {
		// TODO Auto-generated method stub
		
	}

}
