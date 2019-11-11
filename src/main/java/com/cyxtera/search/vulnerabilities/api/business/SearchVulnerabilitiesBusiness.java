package com.cyxtera.search.vulnerabilities.api.business;

import org.springframework.stereotype.Component;

import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityResponse;
import com.cyxtera.search.vulnerabilities.api.processor.ISearchVulnerabilitiesProcessor;

@Component
public class SearchVulnerabilitiesBusiness implements ISearchVulnerabilitiesBusiness {

	@Override
	public VolnurabilityResponse toSearchScoreVulnerabilities(
			ISearchVulnerabilitiesProcessor iSearchVulnerabilitiesProcessor, VolnurabilityRequest request) {
		VolnurabilityResponse response = new VolnurabilityResponse();
		iSearchVulnerabilitiesProcessor.toSearchScoreVulnerabilities(request);
		response.setStatusCode("200");
		return response;
	}

	

}
