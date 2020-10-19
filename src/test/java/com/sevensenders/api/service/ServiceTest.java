package com.sevensenders.api.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sevensenders.api.service.model.CustomFeed;

@SpringBootTest
public class ServiceTest {
	
	@Mock
	Service service;
	
	
	
	@BeforeEach
	void setOutput() {
		when(service.processedFeed("", "")).thenReturn(new ArrayList<CustomFeed>());
	}
	
	@Test
	public void processedFeedTest() {
		assertNotNull(service.processedFeed("", ""));
		
	}
}
