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
	public void toSearchScoreVulnerabilities(VolnurabilityRequest request) {
		
		List<Callable<VulnerabilityBD>> callables = new ArrayList<Callable<VulnerabilityBD>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		

		for (String nameURL : request.getUrl()) {
			Callable<VulnerabilityBD> task = () -> {
				VulnerabilityBD vulnerabilityBD = new VulnerabilityBD();
				
				String nameThread = Thread.currentThread().getName();
				logger.debug("Nombre del hijo en ejecucion " + nameThread);
				vulnerabilityBD.setCostumerName(request.getCostumerName());
				vulnerabilityBD.setUrl(nameURL);
				
				String score = restTemplate.getForObject(serviceURL, String.class, nameURL);
				JSONObject jsonObject = new JSONObject(score);
			    String scoreValue = jsonObject.get("score").toString();
			    logger.info("Score value " + scoreValue);
				vulnerabilityBD.setScore(scoreValue);
				return vulnerabilityBD;
			};
			callables.add(task);
		}

		try {
			List<Future<VulnerabilityBD>> futures = executorService.invokeAll(callables);
			repository.deleteAll();
			futures.forEach(x -> {
				try {
					if (x.get(10, TimeUnit.SECONDS) != null) {
						repository.save(x.get());
					}
				} catch (InterruptedException e) {
					
				} catch (ExecutionException e) {
					
				} catch (TimeoutException e) {
					
				}
			});
			
			System.out.println("Comienzo a escrbir la BD");
			repository.findAll().forEach(System.out::println);
			System.out.println("Fin de escrbir la BD");
			
		} catch (InterruptedException e) {
			
		} finally {
			executorService.shutdownNow();
		}	
	}
	
	@Bean
	public RestTemplate rest() {
	   return new RestTemplate();
	}
}
