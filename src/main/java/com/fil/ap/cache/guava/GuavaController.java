package com.fil.ap.cache.guava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fil.ap.restful.pojo.Greeting;

@RestController
public class GuavaController {

	@Autowired
	private IGuavaService guavaService;
	
	@RequestMapping("/guavaget")
	public String getGuava(String name, long id) {

		Greeting greeting = new Greeting(id, name);
		String response = guavaService.sayHello(greeting);
		
		return response;
	}

	public IGuavaService getGuavaService() {
		return guavaService;
	}

	public void setGuavaService(IGuavaService guavaService) {
		this.guavaService = guavaService;
	}
}
