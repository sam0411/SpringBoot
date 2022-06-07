package com.fil.ap.restful;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.ap.restful.config.GreetingProperties;
import com.fil.ap.restful.pojo.Greeting;

@Profile({ "sit" })
@RestController
public class GreetingControllerLocal {

    @Autowired
    GreetingProperties props;
    
    @RequestMapping("/greeting")
    public Greeting greeting(
    		@RequestParam(value="name", defaultValue="World") String name
    ) {
		Greeting result = null;
		
		try{
			InputStream is = GreetingControllerLocal.class.getResourceAsStream("/mock/greeting_hello_world.json");
			
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
    
    @RequestMapping("/greeting_props")
    public Greeting greetingProperties(
    		@RequestParam(value="name", defaultValue="World") String name
    		) {
		Greeting result = null;
		
		try{
			
			if("Spring Community".equals(name)) {
				
				InputStream is = GreetingControllerLocal.class.getResourceAsStream("/mock/greeting_spring_community.json");
				
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
}