package com.cyxtera.search.vulnerabilities.api.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyxtera.search.vulnerabilities.api.modelo.VulnerabilityBD;
import com.cyxtera.search.vulnerabilities.api.repository.VulnerabilityRepository;

@Component
public class VulnerabilitiesProcessor implements IVulnerabilitiesProcessor {
	
	@Autowired
	VulnerabilityRepository repository;

	@Override
	public List<VulnerabilityBD> getResultVulnerabilities(String costumerName) {
		return repository.findByCostumerName(costumerName);
	}

}
