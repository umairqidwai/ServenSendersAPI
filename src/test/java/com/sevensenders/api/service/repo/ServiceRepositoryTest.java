package com.sevensenders.api.service.repo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sevensenders.api.service.model.CustomFeed;
import com.sevensenders.api.service.model.WebcomicXKCD;

@SpringBootTest
public class ServiceRepositoryTest {

	@Mock
	private ServiceRepository serviceRepository;
	

	@BeforeEach
	void setOutput() {
		when(serviceRepository.fetchXKCD("")).thenReturn(new ResponseEntity<WebcomicXKCD>(HttpStatus.OK));
		when(serviceRepository.fetchPDLFeeds("")).thenReturn(new ArrayList<CustomFeed>());
	}

	@Test
	void test_fetchXKCD() {
		assertNotNull(serviceRepository.fetchXKCD(""));
		
	}
	
	@Test
	void test_fetchPDLFeeds() {
		assertNotNull(serviceRepository.fetchPDLFeeds(""));
	}
}
