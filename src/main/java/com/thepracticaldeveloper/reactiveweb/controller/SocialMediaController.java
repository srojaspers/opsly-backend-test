package com.thepracticaldeveloper.reactiveweb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.thepracticaldeveloper.reactiveweb.domain.Picture;
import com.thepracticaldeveloper.reactiveweb.domain.SocialMedia;
import com.thepracticaldeveloper.reactiveweb.domain.Status;
import com.thepracticaldeveloper.reactiveweb.domain.Tweet;
import com.thepracticaldeveloper.reactiveweb.service.FacebookService;
import com.thepracticaldeveloper.reactiveweb.service.InstagramService;
import com.thepracticaldeveloper.reactiveweb.service.TwitterService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SocialMediaController {



	private static final Logger log = LoggerFactory.getLogger(SocialMediaController.class);

	private TwitterService twitterService;
	private FacebookService facebookService;
	private InstagramService instagramService;
	
	public SocialMediaController(TwitterService twitterService, FacebookService facebookService, InstagramService instagramService) {
		
		this.twitterService = twitterService;
		this.facebookService = facebookService;
		this.instagramService = instagramService;
	}
	
	@GetMapping("/")
	public SocialMedia getSocialMedia() throws InterruptedException, ExecutionException {



		SocialMedia sm = new SocialMedia();
		//Calling services asynchronouly
		CompletableFuture<List<Tweet>> tweets = twitterService.retrieveTweets();
		CompletableFuture<List<Status>> statuses = facebookService.retrieveStatuses();
		CompletableFuture<List<Picture>> pictures = instagramService.retrievePictures();
		
		// Wait until they are all done
		CompletableFuture.allOf(tweets,statuses, pictures).join();

		
		sm.setTwitter(tweets.get());
		sm.setFacebook(statuses.get());
		sm.setInstagram(pictures.get());


		return sm;
				
	}

}
