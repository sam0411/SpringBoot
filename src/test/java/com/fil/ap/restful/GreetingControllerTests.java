/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fil.ap.restful;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fil.ap.cache.guava.GuavaService;
import com.fil.ap.restful.pojo.Greeting;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"uat"})
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GuavaService guavaServiceMock;
    
    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
    	
    	Greeting greeting = new Greeting(new Long(111), "Alex");
    	
    	BDDMockito.given(this.guavaServiceMock.sayHello(greeting)).willReturn("Hello Alex");
    	
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/greeting").param("name", "Spring Community")).andReturn();
    
        int status = result.getResponse().getStatus();
        byte[] bytes = result.getResponse().getContentAsByteArray();

		String json = new String(bytes, "UTF-8");

		System.out.println(json);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		TypeReference<Greeting> typeRef = new TypeReference<Greeting>(){};
		Greeting bean = mapper.readValue(json, typeRef);
		
        Assert.assertEquals(
        		"HTTP Response Status is not 200", 
        		200, 
        		status
        );
        
        Assert.assertEquals("Content is incorrect", 
        		"Hello, Spring Community!", 
        		bean.getContent()
        );
        
//        Assert.assertEquals("ID is incorrect", 
//        		1, 
//        		bean.getId()
//        );
    }

}
