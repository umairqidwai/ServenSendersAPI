package com.sevensenders.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.sevensenders.api.service.model.CustomFeed;

@SpringBootTest
public class ServiceControllerTest {
	
	@Mock
	ServiceController serviceController;
	
	@Mock
	List<CustomFeed> customFeedList;
	
	@Test
	public void test_feed() {
		when(serviceController.feed()).thenReturn(customFeedList);
		assertEquals(customFeedList, serviceController.feed());
	}
	
}
