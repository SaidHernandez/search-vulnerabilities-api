package com.cyxtera.search.vulnerabilities.api.business;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityResponse;
import com.cyxtera.search.vulnerabilities.api.processor.ISearchVulnerabilitiesProcessor;

@Component
public class SearchVulnerabilitiesBusiness implements ISearchVulnerabilitiesBusiness {

	@Override
	public VolnurabilityResponse toSearchScoreVulnerabilities(
			ISearchVulnerabilitiesProcessor iSearchVulnerabilitiesProcessor, VolnurabilityRequest request) {
		logger.info("Init the toSearchScoreVulnerabilities method " );
		try {
			iSearchVulnerabilitiesProcessor.toSearchScoreVulnerabilities(request);
			logger.info("End successful ");
			return buildMessageResponse("The informations is consulted successful", String.valueOf(HttpStatus.OK.value()));
		} catch (Exception e) {
			return buildMessageResponse("There is a problem searching for the URL rating", String.valueOf(HttpStatus.NO_CONTENT.value()));
		}
		
	}

	@Override
	public VolnurabilityResponse buildMessageResponse(String message, String code) {
		VolnurabilityResponse response = new VolnurabilityResponse(); 
		response.setStatusMessage(message);
		response.setStatusCode(code);
		response.setIdTransaction(UUID.randomUUID().toString());
	    response.setDate(new Date());
		return response;
	}

	

}
