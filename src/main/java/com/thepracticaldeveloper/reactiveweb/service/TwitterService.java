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

import com.thepracticaldeveloper.reactiveweb.domain.Tweet;






@Service
public class TwitterService {

	private static final Logger log = LoggerFactory.getLogger(TwitterService.class);

	private final RestTemplate restTemplate;

	public TwitterService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<List<Tweet>> retrieveTweets() throws InterruptedException {

		String url = "https://takehome.io/twitter";

		List<Tweet> results = new ArrayList<Tweet>();


		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		int maxAttempts = 5 ;

		for(int count = 0 ; count < maxAttempts ; count++) {
			try {
				
				ResponseEntity<List<Tweet>> tweetResponse = restTemplate.exchange(url, HttpMethod.GET,
						entity, new ParameterizedTypeReference<List<Tweet>>() {
						});
				
				
				results = tweetResponse.getBody();

				count = maxAttempts; //don't retry

				log.debug("Tweet Size IS: " + results.size());

				
			} catch (Exception e) {
				
				log.error(e.getLocalizedMessage());
				e.printStackTrace();
				
			}
		}




		return CompletableFuture.completedFuture(results);
	}

}
