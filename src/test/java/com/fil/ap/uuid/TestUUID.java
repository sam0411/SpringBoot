package com.fil.ap.uuid;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class TestUUID {

	@Test
	public void generateUUID() {
		
		for(int i=0;i<10;i++){
			
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
		}
	}
	
	@Test
	public void hashUUIDAES256() throws Exception {
		
		String source = "8a8eb4b0b3d747999185d17f4efc3ee0";
		String key = "testing1234";
		
		byte[] bytes = AES256Utils.encrypt(source.getBytes(), key.getBytes());
		
		String target = new String(bytes);
		
		System.out.println(target);
	}
}
