package com.sample.namesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;


@RestController
public class UserNameSenderController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/getWelcomeMessage")
	public String getWelcomeMessageForUser(@RequestParam("name")String name){
		System.out.println("Getting welcome message for user name " + name);
		String response = restTemplate.exchange("http://localHost:8091/getWelcomeMessageForUser/{name}", HttpMethod.GET, null, String.class, name).getBody();
		return response;
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
