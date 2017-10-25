package com.fil.ap.restful;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fil.ap.restful.config.GreetingProperties;
import com.fil.ap.restful.pojo.Greeting;

@RestController
@Profile({ "uat" })
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    GreetingProperties props;
    
    @RequestMapping("/greeting")
    public Greeting greeting(
    		@RequestParam(value="name", defaultValue="World") String name
    		) {
        return new Greeting(
        		counter.incrementAndGet(),
                String.format(template, name)
        );
    }
    
    @RequestMapping("/greeting_props")
    public Greeting greetingProperties(
    		@RequestParam(value="name", defaultValue="World") String name
    		) {
    	
    	long ct = counter.incrementAndGet();
    	System.out.println(ct + "---" + name);
        return new Greeting(
        		ct,
        		props.getGreeting()+name
        );
    }
    
    @RequestMapping(value = "/greeting_post",method = RequestMethod.POST)
    @ResponseBody
    public Greeting list(@RequestBody Greeting greeting) throws InterruptedException{
    	
    	long cc = counter.incrementAndGet();
    	
    	if(cc < 3L) {
    		
    		System.out.println(cc + " Test retry, sleep 5 seconds\n\n");
    		
    		Thread.sleep(5000);
    	}
    	
    	Greeting result = new Greeting(greeting.getId()+1, greeting.getContent());

        return result;
    }


}