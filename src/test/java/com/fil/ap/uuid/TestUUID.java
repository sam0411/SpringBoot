package com.fil.ap.uuid;

import java.util.UUID;

import org.junit.Test;

public class TestUUID {

	@Test
	public void generateUUID() {
		
		for(int i=0;i<10;i++){
			
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
		}
	}
}
