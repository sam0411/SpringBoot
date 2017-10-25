package com.fil.ap.restful.feign;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fil.ap.restful.pojo.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = {"uat"})
public class GreetingFeighUnitTest {

	@Autowired
    private GreetingFeign mock;
	
	@Test
    public void noParamGetTest() throws Exception {

		mock.getGreeting();
    }
	
	@Test
    public void postTest() throws Exception {

		Greeting greeting = new Greeting(1L, "Hello Alex");
		
		mock.sayHello(greeting);
    }
}
