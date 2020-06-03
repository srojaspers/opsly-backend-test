# Opsly Backend test

## Running the application

Execute `mvnw spring-boot:run`.

Once the application is started access to http://localhost:3000 either using a browser or curl

## Original requirement

Your mission:
A client needs to know what is happening on the social networks. All of them. Right now.

The three social networks the client is interested in are:

https://takehome.io/twitter

https://takehome.io/facebook

https://takehome.io/instagram

Because these social networks are so webscale, they don't always respond predictably. The delay in their response almost appears like someone waited for a random integer of seconds before responding!

Also, sometimes they will respond with an error. This error will not be valid JSON. Life's hard sometimes.

The client needs to be able to run your thing, then issue the command:

curl localhost:3000
And get back a JSON response of the output from the three social networks in the format:

{ twitter: [tweets], facebook: [statuses], instagram: [photos] }
Order isn't important.

This should be a quick little task, but the client is paying us A Billion dollars for it so make sure your implementation is as robust as it is beautiful.

Don't forget to git push regularly.

Have fun!

## Implementation details

To implement this exercise I have used SpringBoot and its functionality to easly create HTTP services and to handle asynchronous code exection. These are the changes implemented:

- Change the default port from 8080 to 3000: Just by adding server.port=3000 to application.properties
- Implement the RestCotroller to handle the requests to "/". Please see SocialMediaController Class
- Implement a services responsible for retrieving data from Facebook, Instagram and Twitter. Please see the classes within the package com.thepracticaldeveloper.reactiveweb.service
- Implemented the domain layer to define objects for Picture, Tweet, Status and SocialMedia. Please see classes within the package com.thepracticaldeveloper.reactiveweb.domain


## Handling the unpredictability the services using Asynchronous computation and error handling

The services for Twitter, Facebook and Instagram are quite unreliable in terms of performance (response time) and results. They will somtimes respond with an error. 

I have used Java's CompletableFuture to perform the calls to the three services asynchronously so that they don't block each other. Then I have used the CompletableFuture features to sync the response from the different calls before unifying everything within a single response in Json Format to be return to the end user. (Please see SocialMediaController.java)

**Error handling**
Given the unpredicatibility of the services I have implemented a retry functionality (maxRetries set to 5). This way, if one of the services return an error, it will retry (up to 5 times) until the service returns data. Please FacebookService.java, Instagram.java and Twitter.java


