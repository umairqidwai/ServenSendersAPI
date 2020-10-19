package com.sevensenders.api.service.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sevensenders.api.service.helper.FeedHelper;
import com.sevensenders.api.service.model.CustomFeed;
import com.sevensenders.api.service.model.WebcomicXKCD;

public class ServiceRepositoryImpl implements ServiceRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private FeedHelper feedHelper;

	public ServiceRepositoryImpl() {
	}

	@Override
	public ResponseEntity<WebcomicXKCD> fetchXKCD(String url) {
		return restTemplate.getForEntity(url, WebcomicXKCD.class);
	}

	@Override
	public List<CustomFeed> fetchPDLFeeds(String url) {
		return feedHelper.XMLFeedReader(url);
	}

}
