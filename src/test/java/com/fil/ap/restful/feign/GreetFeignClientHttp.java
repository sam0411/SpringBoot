package com.fil.ap.restful.feign;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fil.ap.restful.pojo.Greeting;

import feign.Feign;
import feign.Request.Options;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Component
@Profile({ "uat" })
public class GreetFeignClientHttp implements GreetingFeign {

	private static final String url = "http://localhost:8080";

	@Override
	public Greeting getGreeting() {
		
		GreetingFeign greetingResource = Feign.builder()
				.encoder(new JacksonEncoder())
		        .decoder(new JacksonDecoder())
		        .target(GreetingFeign.class, url);
		
		Greeting greeting = greetingResource.getGreeting();
		
		return greeting;
	}

	@Override
	public Greeting getGreetingProperty(String name) {
		
		GreetingFeign greetingResource = Feign.builder()
				.encoder(new JacksonEncoder())
		        .decoder(new JacksonDecoder())
		        .target(GreetingFeign.class, url);
		
		Greeting greeting = greetingResource.getGreetingProperty(name);
		
		return greeting;
	}

	@Override
	public Greeting sayHello(Greeting greeting) {
		
		GreetingFeign greetingResource = Feign.builder()
				.encoder(new JacksonEncoder())
		        .decoder(new JacksonDecoder())
		        .options(new Options(1000, 3500))
                .retryer(new Retryer.Default(1000, 1000, 3))
		        .target(GreetingFeign.class, url);
		
		Greeting result = greetingResource.sayHello(greeting);
		
		System.out.println(result.getId() + " - " + result.getContent());
		
		return result;
	}
}
