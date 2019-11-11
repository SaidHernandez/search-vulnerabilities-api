package com.cyxtera.search.vulnerabilities.api.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cyxtera.search.vulnerabilities.api.exception.SearchVulnerabilitiesException;
import com.cyxtera.search.vulnerabilities.api.modelo.VolnurabilityRequest;
import com.cyxtera.search.vulnerabilities.api.modelo.VulnerabilityBD;
import com.cyxtera.search.vulnerabilities.api.repository.VulnerabilityRepository;


@Component
public class SearchVulnerabilitiesProcessor implements ISearchVulnerabilitiesProcessor {
    
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	VulnerabilityRepository repository;
	
	@Value("${value.serviceURL}")
	String serviceURL;
	
	@Override
	public void toSearchScoreVulnerabilities(VolnurabilityRequest request) throws SearchVulnerabilitiesException{
		logger.info("Init the toSearchScoreVulnerabilities method ");
		
		List<Callable<VulnerabilityBD>> callables = new ArrayList<Callable<VulnerabilityBD>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		
		for (String nameURL : request.getUrl()) {
			Callable<VulnerabilityBD> task = () -> {
				VulnerabilityBD vulnerabilityBD = new VulnerabilityBD();
				logger.info("Init the construction of the callables");
				
				String nameThread = Thread.currentThread().getName();
				logger.debug("Name the thread " + nameThread);
				vulnerabilityBD.setCostumerName(request.getCostumerName());
				vulnerabilityBD.setUrl(nameURL);
				
				String score = restTemplate.getForObject(serviceURL, String.class, nameURL);
				JSONObject jsonObject = new JSONObject(score);
			    String scoreValue = jsonObject.get("score").toString();
			    logger.info("Score value " + scoreValue);
				vulnerabilityBD.setScore(scoreValue);
				return vulnerabilityBD;
			};
			logger.info("End the construction of the callables");
			callables.add(task);
			logger.debug("Size the callables " + callables.size());
		}

		try {
			logger.info("Execution the threads ");
			List<Future<VulnerabilityBD>> futures = executorService.invokeAll(callables);
			logger.debug("Execute the succesful " + callables.size());
			
			futures.forEach(x -> {
				try {
					if (x.get(10, TimeUnit.SECONDS) != null) {
						logger.info("Saving the information ");
						repository.save(x.get());
					}
				} catch (InterruptedException e) {
					logger.error("There is a problem  InterruptedException");	
				} catch (ExecutionException e) {
					logger.error("There is a problem  ExecutionException");	
				} catch (TimeoutException e) {
					logger.error("There is a problem  TimeoutException");	
				}
			});
			logger.debug("Writing in the BD... ");
			repository.findAll().forEach(System.out::println);
			logger.debug("Information saved!");
		} catch (InterruptedException e) {
			throw new SearchVulnerabilitiesException("There is a problem in the toSearchScoreVulnerabilities method"); 
		} finally {
			executorService.shutdownNow();
		}	
	}
	
	/**
	 * Rest client
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate rest() {
	   return new RestTemplate();
	}
}
