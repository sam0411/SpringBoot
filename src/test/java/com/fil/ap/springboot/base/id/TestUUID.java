package com.fil.ap.springboot.base.id;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUUID {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestUUID.class);
	
	private static void generateUUID() {
		
		for(int i=0; i<10; i++){
			
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			LOGGER.info(uuid);
		}
	}
	
	public static void main(String[] args) {
		
		generateUUID();
	}
}
