package com.fil.ap.cache.guava;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fil.ap.restful.pojo.Greeting;

@Service
public class GuavaService implements IGuavaService {

	@Override
	@Cacheable(value = "guavacache", key = "'guava'.concat(#greeting.id.toString()).concat(#greeting.content.toString())")
	public String sayHello(Greeting greeting) {
		
		System.out.println("\n\nthrough real call\n\n");
		
		String response = "Hello Local Cache Guava!";
		
		return response;
	}

}
