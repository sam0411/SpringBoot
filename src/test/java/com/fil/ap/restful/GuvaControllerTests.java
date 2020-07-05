package com.fil.ap.restful;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fil.ap.cache.guava.GuavaService;
import com.fil.ap.cache.guava.IGuavaService;
import com.fil.ap.restful.pojo.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"uat"})
public class GuvaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IGuavaService guavaServiceMock;
    
    @Test
    public void mockito() throws Exception {
    	
    	Greeting greeting = new Greeting(new Long(111), "Alex");
    	
    	BDDMockito.given(this.guavaServiceMock.sayHello(greeting)).willReturn("Hello Alex");
    	
    	MvcResult result = this.mockMvc.perform(get("/guavaget").param("id", "111").param("name", "Alex")).andReturn();
    
    	int status = result.getResponse().getStatus();
        byte[] bytes = result.getResponse().getContentAsByteArray();

		String json = new String(bytes, "UTF-8");

		System.out.println(json);
    }
}
