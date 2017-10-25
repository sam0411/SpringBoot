package com.fil.ap.restful.feign;

import com.fil.ap.restful.pojo.Greeting;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
 
@Headers("Accept: application/json")
public interface GreetingFeign {

	@RequestLine("GET /greeting")
	Greeting getGreeting();
	
	@RequestLine("GET /greeting_props?name={name}")
	Greeting getGreetingProperty(@Param("name") String name);
	
	@Headers({"Content-Type: application/json"})
    @RequestLine("POST /greeting_post")
	Greeting sayHello(Greeting greeting);
}
