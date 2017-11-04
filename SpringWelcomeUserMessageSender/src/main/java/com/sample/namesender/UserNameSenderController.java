package com.sample.namesender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
public class UserNameSenderController {

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${baseurl.namereceiver}")
	String baseUrl;
	
	@Value("${urlpattern.getWelcomeMessageUrlPattern}")
	String getWelcomeMessagePattern;
	
	ResponseEntity<String> response = null;
	
	@RequestMapping("/getWelcomeMessage")
	public ResponseEntity<String> getWelcomeMessageForUser(@RequestParam("name")String name){
		System.out.println("Getting welcome message for user name " + name);
		try{
			 response = restTemplate.exchange(baseUrl + getWelcomeMessagePattern, HttpMethod.GET, null, String.class, name);
		}
		catch (HttpStatusCodeException ex) {
		    int statusCode = ex.getStatusCode().value();
		    
		    System.out.println(statusCode);
		    if(statusCode >= 500)
		    	return new ResponseEntity<String>("Something Wrong Happened", HttpStatus.INTERNAL_SERVER_ERROR);
		    else if(statusCode >= 404)
		    	return new ResponseEntity<String>("Welcome Message URL pattern For Receiver NOT configured properly", HttpStatus.NOT_FOUND);
		    
		}
	
		
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
