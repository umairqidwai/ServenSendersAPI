package com.sevensenders.api.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevensenders.api.service.model.CustomFeed;

@RestController
public class ServiceController {

	@Autowired
	private Service service;
	private String PDLUrl = "http://feeds.feedburner.com/PoorlyDrawnLines";
	private String webComicUrl = "https://xkcd.com/info.0.json";
	

	@GetMapping("/")
	public List<CustomFeed> feed() {
		List<CustomFeed> customFeedList = service.processedFeed(PDLUrl, webComicUrl);
		Collections.reverse(customFeedList);
		return customFeedList;
	}

}
