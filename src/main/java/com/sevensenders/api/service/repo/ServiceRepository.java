package com.sevensenders.api.service.repo;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sevensenders.api.service.model.CustomFeed;
import com.sevensenders.api.service.model.WebcomicXKCD;

public interface ServiceRepository {
	
	 ResponseEntity<WebcomicXKCD> fetchXKCD(String url);
	 
	 List<CustomFeed> fetchPDLFeeds(String url);
	
}
