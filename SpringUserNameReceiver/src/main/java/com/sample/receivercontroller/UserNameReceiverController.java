package com.sample.receivercontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserNameReceiverController {

	@RequestMapping(value="/getWelcomeMessageForUser/{name}")
	public String sendWelcomeMessageResponseBack(@PathVariable String name){
		
		return "Welcome " + name;
	}
	
	
}
