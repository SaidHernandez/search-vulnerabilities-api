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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cyxtera.search.vulnerabilities.api.modelo.VulnerabilityBD;
import com.cyxtera.search.vulnerabilities.api.repository.VulnerabilityRepository;

public class SearchVulnerabilitiesProcessor implements ISearchVulnerabilitiesProcessor {
    
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	VulnerabilityRepository repository;
	
	@Value("${value.serviceURL}")
	String serviceURL;
	
	@Override
	public void toSearchScoreVulnerabilities(List<String> namesURL) {
		
		List<Callable<String>> callables = new ArrayList<Callable<String>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		

		for (String nameURL : namesURL) {
			Callable<String> task = () -> {
				String nameThread = Thread.currentThread().getName();
				logger.debug("Nombre del hijo en ejecucion " + nameThread);
				return restTemplate.getForObject(serviceURL, String.class, nameURL);
			};
			callables.add(task);
		}

		try {
			List<Future<String>> futures = executorService.invokeAll(callables);
			
			futures.forEach(x -> {
				try {
					if (x.get(10, TimeUnit.SECONDS) != null) {
						VulnerabilityBD vulnerabilityBD = new VulnerabilityBD("prueba", "URL 1", x.get()); 
						repository.save(vulnerabilityBD);
					}
				} catch (InterruptedException e) {
					
				} catch (ExecutionException e) {
					
				} catch (TimeoutException e) {
					
				}
			});
			
			System.out.println("Comienzo a escrbir la BD");
			repository.findAll().forEach(System.out::println);
			
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
