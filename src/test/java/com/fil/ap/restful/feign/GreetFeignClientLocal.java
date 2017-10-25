package com.fil.ap.restful.feign;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.ap.restful.pojo.Greeting;

@Component
@Profile({ "sit" })
public class GreetFeignClientLocal implements GreetingFeign {

	@Override
	public Greeting getGreeting() {
		
		Greeting result = null;
		
		try{
			InputStream is = GreetFeignClientLocal.class.getResourceAsStream("/mock/greeting_hello_world.json");
			
			byte[] bytes = IOUtils.toByteArray(is);
			
			String json = new String(bytes, "UTF-8");

			System.out.println(json);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			TypeReference<Greeting> typeRef = new TypeReference<Greeting>(){};
			result = mapper.readValue(json, typeRef);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Greeting getGreetingProperty(String name) {

		Greeting result = null;
		
		try{
			
			if("Spring Community".equals(name)) {
				
				InputStream is = GreetFeignClientLocal.class.getResourceAsStream("/mock/greeting_spring_community.json");
				
				byte[] bytes = IOUtils.toByteArray(is);
				
				String json = new String(bytes, "UTF-8");

				System.out.println(json);
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				TypeReference<Greeting> typeRef = new TypeReference<Greeting>(){};
				result = mapper.readValue(json, typeRef);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Greeting sayHello(Greeting greeting) {

		return null;
	}
}
