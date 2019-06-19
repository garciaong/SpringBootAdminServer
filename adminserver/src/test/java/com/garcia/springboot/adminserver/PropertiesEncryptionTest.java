package com.garcia.springboot.adminserver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//This will add application context
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesEncryptionTest {

	@Value("${test.password}")
	private String password;
	
	@Test
	public void testEncryption() {
		System.setProperty("jasypt.encryptor.password", "password");
	    assertEquals("123", password);
	}
	
}
