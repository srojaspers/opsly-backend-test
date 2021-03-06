package com.thepracticaldeveloper.reactiveweb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thepracticaldeveloper.reactiveweb.domain.Picture;








@Service
public class InstagramService {

	private static final Logger log = LoggerFactory.getLogger(InstagramService.class);

	private final RestTemplate restTemplate;

	public InstagramService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<List<Picture>> retrievePictures() throws InterruptedException {

		String url = "https://takehome.io/instagram";


		List<Picture> results = new ArrayList<Picture>();


		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		//Max number of retry attempts
		int maxAttempts = 5 ;
		
		for(int count = 0 ; count < maxAttempts ; count++) {
			try {
				
				ResponseEntity<List<Picture>> pictureResponse = restTemplate.exchange(url, HttpMethod.GET,
						entity, new ParameterizedTypeReference<List<Picture>>() {
						});
				
				
				results = pictureResponse.getBody();

				count = maxAttempts; //don't retry

				log.debug("Picture Size IS: " + results.size());

				
			} catch (Exception e) {
				
				log.error(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
		}
		
		

		


		return CompletableFuture.completedFuture(results);
	}

}
