package com.cyxtera.search.vulnerabilities.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/app")
public class SearchVulnerabilitiesController implements ISearchVulnerabilitiesController {
    
	@Override
	@RequestMapping(value={"/v1/vulnerability"},  produces="application/json", consumes = "application/json", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.OK)
	public String toSearchVulnerabilities() {
		logger.info("Begin the searchVulnerabilities method");
		
		
		
		  
		 return "result successful result";
	}

	@Override
	public void getResultVulnerabilities() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toGenerateReportVulnerabilities() {
		// TODO Auto-generated method stub
		
	}

}
